#! /usr/bin/perl
use strict;
use WWW::Mechanize;
use IO::Handle;
use utf8;
use encoding ':locale';
use Encode qw(decode encode);
my $mech = WWW::Mechanize->new();
my $query;
my $filename = $ARGV[0];
my $url = 'http://magiccards.info/query?q=';

my @editions = ( 'Labyrinth des Drachen', 'Gildensturm', 'Rückkehr nach Ravnica', 'Avacyns Rückkehr', 'Dunkles Erwachen', 'Innistrad', 'Das neue Phyrexia', 'Belagertes Mirrodin', 'Die Narben von Mirrodin', 'Aufstieg der Eldrazi', 'Weltenerwachen', 'Zendikar', 'Alara Reborn', 'Conflux', 'Fragmente von Alara', 'Abendkühle', 'Schattenmoor', 'Morgenluft', 'Lorwyn', 'Blick in die Zukunft', 'Weltenchaos', 'Zeitspirale', 'Zeitspirale Timeshifted', 'Kälteeinbruch', 'Allianzen', 'Eiszeit', 'Zwietracht', 'Gildenbund', 'Ravnica: Stadt der Gilden', 'Retter von Kamigawa', 'Verräter von Kamigawa', 'Meister von Kamigawa', 'Fünfte Morgenröte', 'Nachtstahl', 'Mirrodin', 'Plagen', 'Legionen', 'Aufmarsch', 'Abrechnung', 'Qualen', 'Odyssee', 'Apokalypse', 'Weltenwechsel', 'Invasion', 'Prophezeihung', 'Nemesis', 'Merkadische Masken', 'Urzas Schicksal', 'Urzas Vermächtnis', 'Urzas Saga', 'Exodus', 'Felsenburg', 'Sturmwind', 'Wetterlicht', 'Visionen', 'Trugbilder', 'Heimatländer', 'Hauptset Magic 2014', 'Magic 2013', 'Magic 2012', 'Magic 2011', 'Magic 2010', 'Zehnte Edition', 'Haupt-Set - Neunte Edition', 'Haupt-Set - Achte Edition', 'Siebte Edition', 'Classic Sechste Edition', 'Fünfte Edition', 'Vierte Edition', 'Unlimitierte Auflage', 'Limitierte Auflage' );


my @conditions = ("mint","near-mint","lightly played","played","heavily played","damaged");
my $type;
my $subtype;
my $power;
my $hp;
my $mana_cost;
my $tot_mana_cost;
my $cardtext;
my $flavor_text;
my $edition;
my $rarity;
my $cardimage;
my $condition;
#pattern to extract desired info
my $pattern_typeline = '<p>(.*),\s\n\s*([0-9A-Z]{1,})\s\(([0-9]{1,})\)\n.*<\/p>';
my $pattern_typeline_creature = '<p>(.*)\s—\s(.*)\s(\d{1,2}|\*)\/(\d{1,2}|\*),\s\n\s*([0-9A-Z]{1,})\s\(([0-9]{1,})\)\n.*<\/p>';
my $pattern_typeline_other = '<p>(.*)\s—\s(.*)\n.*';
my $pattern_ctext = '<p class="ctext"><b>(.*)<\/b><\/p>';
my $pattern_flavortext = '<p><i>(.*)<\/i><\/p>';
my $pattern_edition_rarity = '';
my $pattern_scan_url = '\"(http:\/\/magiccards\.info\/scan.*)\"';


my $file = $filename; 
open my $info, $file or die "Could not open $file: $!";
open(my $fh, '>', 'mtg_sammlung_fetched.csv') or die "Could not open file '$filename' $!";
print $fh "name,type,subtype,power,hp,mana_cost,tot_mana_cost,cardtext,flavor_text,edition,rarity,condition,cardimage,foil\n";
binmode $fh, ':utf8';
while( my $line = <$info>)  {   
	my @cols = split(',',$line);
	my $cardname = $cols[0];
	my $cardcond = $cols[2];
	my $cardisfoil = $cols[3];
	print "\nprocessing card $cols[0]\n";
	my $line = lookup_card($cardname,$cardcond);
	$cols[3] =~ s/\n//g;
	$cols[3] =~ s/\R//g;
	$cols[2] =~ s/\n//g;
	$cols[2] =~ s/\R//g;
	print $fh "$line,$cols[3]\n";    
	$fh->flush();
}

close $fh;
close $info;

sub lookup_card{
	$type='';
	$subtype='';
	$power='';
	$hp='';
	$mana_cost='';
	$tot_mana_cost='';
	$cardtext='';
	$flavor_text='';
	$edition='';
	$rarity='';
	$cardimage='';
	$condition='';
	

	$query = shift(@_);
	$condition = shift(@_);
	my $name = $query;
	$query = lc $query;
	$query =~ s/\s/\+/g;
	my $queryurl = "$url$query";


	$mech->get($queryurl);

#	my $html = $mech->content();
	my $html = $mech->response->decoded_content;
#print "\n\n\n\n\n\n\n\n\n\n$html\n\n\n\n\n\n\n\n\n\n";
	if($html =~ $pattern_typeline){
		$type = $1;
		$mana_cost = $2;
		$tot_mana_cost = $3;
	}
	if($html =~ $pattern_typeline_other){
		$type = $1;
		$subtype = $2;
	}
	if($html =~ $pattern_typeline_creature){
		$type = $1;
		$subtype = $2;
		$power = $3;
		$hp = $4;
		$mana_cost = $5;
		$tot_mana_cost = $6;
	}
	if($html =~ $pattern_scan_url){
		$cardimage = $1;
	}

	if($html =~ $pattern_ctext){   
		$cardtext = $1;
	}
	if($html =~ $pattern_flavortext){
		$flavor_text = $1;
	}
	my @multiple_eds;
	my @multiple_freqs;
	foreach my $pattern (@editions){
		$pattern =~ s/\s/\\s/g;
		if($html =~ m/>($pattern).*\s\((.*)\)</){
			push @multiple_eds,$1;
			push @multiple_freqs,$2;
		}
	}
	print "\n\n$name\n";
	if(@multiple_eds > 1){
		my $counter = 0;
		print "edition wählen:\n";
		foreach my $ed (@multiple_eds){
			print $counter++,": $ed\n";
		}
		my $ed_idx = <STDIN>;
		$edition = $multiple_eds[$ed_idx];
		$rarity = $multiple_freqs[$ed_idx];
	}else{
		$edition = $multiple_eds[0];
		$rarity = $multiple_freqs[0];
	}
#	my $ctr = 0;
#	print "Kartenzustand wählen:\n";
#	foreach my $cond (@conditions){
#		print $ctr++,": $cond\n";
#	}
#	my $cond_idx = <STDIN>;
#	my	$condition = $conditions[$cond_idx];
#
	#stripping some special chars
	$type =~ s/\R//g;
	$subtype =~ s/\R//g;
	$subtype =~ s/,/ /g;
	$power =~ s/\R//g;
	$hp =~ s/\R//g;
	$mana_cost =~ s/\R//g;
	$tot_mana_cost =~ s/\R//g;
	$cardtext =~ s/\R//g;
	$cardtext =~ s/\n//g;
	$cardtext =~ s/,/ /g;
	$cardtext =~ s/„/ /g;
	$cardtext =~ s/"/ /g;
	$flavor_text =~ s/\R//g;
	$flavor_text =~ s/\n//g;
	$flavor_text =~ s/,/ /g;
	$flavor_text =~ s/„/ /g;
	$flavor_text =~ s/"/ /g;
	$edition =~ s/\R//g;
	$rarity =~ s/\R//g;
	$cardimage =~ s/\R//g;
	$name =~ s/\R//g;
	$name =~ s/\n//g;
	$name =~ s/,/ /g;


	print "\n\n$name\n";
	print "==============================================\n";
	print "typ:\t\t\t$type\n";
	print "subtyp:\t\t\t$subtype\n";
	print "power:\t\t\t$power\n";
	print "hp:\t\t\t$hp\n";
	print "mana:\t\t\t$mana_cost\n";
	print "total:\t\t\t$tot_mana_cost\n";
	print "cardtext:\t\t\t$cardtext\n";
	print "flavor:\t\t\t$flavor_text\n";
	print "edition:\t\t\t$edition\n";
	print "rarity:\t\t\t$rarity\n";
	print "condition:\t\t\t$condition\n";
	print "cardimage:\t\t\t$cardimage\n";
	my $entry = "$name,$type,$subtype,$power,$hp,$mana_cost,$tot_mana_cost,$cardtext,$flavor_text,$edition,$rarity,$condition,$cardimage";
	return "$entry";
}
