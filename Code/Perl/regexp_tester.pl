#!/usr/bin/perl -w
use strict;

my $text = '    <b>#149 (Christopher Moeller)</b><br>';
my $pattern = '#(\d{1,3})\s\((.*)\)';

if($text =~ $pattern){
	print "Nr: $1\n";
	print "Artist: $2";
}else{
	print "no match\n";
}

