DROP TABLE IF EXISTS "Edition";
DROP TABLE IF EXISTS "MagicCard";
//Create Edition:

CREATE TABLE PUBLIC."Edition"
(
  "editionId"  VARCHAR NOT NULL,
  "name"       VARCHAR NULL,
  "numOfCards" INT     NULL,
  PRIMARY KEY ("editionId")
);
ALTER TABLE PUBLIC."Edition" ADD CONSTRAINT "unique_editionId" UNIQUE ("editionId");

//Create MagicCard
CREATE TABLE PUBLIC."MagicCard"
(
  "REF_EDITION"  VARCHAR NOT NULL,
  "cardNumber"   VARCHAR NOT NULL DEFAULT '',
  "type"         VARCHAR NOT NULL,
  "subType"      VARCHAR NOT NULL,
  "manaCost"     VARCHAR NOT NULL,
  "convManaCost" INT     NOT NULL,
  "power"        INT     NOT NULL DEFAULT 0,
  "toughness"    INT     NOT NULL DEFAULT 0,
  "imageURL"     VARCHAR NOT NULL DEFAULT '',
  "cardText"     VARCHAR NOT NULL,
  "flavorText"   VARCHAR NOT NULL DEFAULT '',
  "artist"       VARCHAR NOT NULL DEFAULT '',
  "rarity"       INT     NOT NULL DEFAULT 0,
  "name"         VARCHAR NOT NULL,
  "cardId"       VARCHAR NOT NULL AS concat("REF_EDITION", '_', "cardNumber"),
  PRIMARY KEY ("cardId")
);
ALTER TABLE "MagicCard" ADD CONSTRAINT "unique_cardId" UNIQUE ("cardId");
CREATE INDEX cardDescIdx ON "MagicCard" ("cardId");

CREATE TABLE PUBLIC."PhysicalCard"
(
  "ID"            BIGINT  NOT NULL AUTO_INCREMENT,
  "REF_CARD"      VARCHAR NOT NULL,
  "REF_CONTAINER" INT     NOT NULL,
  "CONDITION"     INT     NOT NULL,
  "LANG"          INT     NOT NULL,
  PRIMARY KEY ("ID")
);

CREATE TABLE PUBLIC."Container"
(
  "ID"          BIGINT  NOT NULL AUTO_INCREMENT,
  "NAME"        VARCHAR NOT NULL,
  "DESCRIPTION" VARCHAR NULL,
  PRIMARY KEY ("ID")
);


CREATE TABLE PUBLIC."Deck"
(
  "ID"   BIGINT  NOT NULL AUTO_INCREMENT,
  "NAME" VARCHAR NOT NULL,
  PRIMARY KEY ("ID")
);

CREATE TABLE PUBLIC."CardDescriptionXDeck"
(
  "ID"       BIGINT  NOT NULL AUTO_INCREMENT,
  "REF_CARD" VARCHAR NOT NULL,
  "REF_DECK" VARCHAR NOT NULL,
  PRIMARY KEY ("ID")
);

