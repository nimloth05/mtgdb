DROP TABLE IF EXISTS "Edition";
DROP TABLE IF EXISTS "CardDescription";
//Create Edition:

CREATE TABLE PUBLIC."Edition"
(
  "editionId"  VARCHAR NOT NULL,
  "name"       VARCHAR NULL,
  "numOfCards" INT     NULL,
  "lang"       VARCHAR NULL,
  PRIMARY KEY ("editionId")
);
ALTER TABLE PUBLIC."Edition" ADD CONSTRAINT "unique_editionId" UNIQUE ("editionId");

//Create CardDescription
CREATE TABLE PUBLIC."CardDescription"
(
  "REF_EDITION"   VARCHAR NOT NULL,
  "cardNumber"    VARCHAR NOT NULL DEFAULT '',
  "type"          VARCHAR NOT NULL,
  "subType"       VARCHAR NOT NULL,
  "manaCost"      VARCHAR NOT NULL,
  "convManaConst" INT     NOT NULL,
  "power"         INT     NOT NULL DEFAULT 0,
  "toughness"     INT     NOT NULL DEFAULT 0,
  "imageURL"      VARCHAR NOT NULL DEFAULT '',
  "cardText"      VARCHAR NOT NULL,
  "flavorText"    VARCHAR NOT NULL DEFAULT '',
  "artist"        VARCHAR NOT NULL DEFAULT '',
  "rarity"        INT     NOT NULL DEFAULT 0,
  "name"          VARCHAR NOT NULL,
  "cardId"        VARCHAR NOT NULL AS concat("REF_EDITION", '_', "cardNumber"),
  PRIMARY KEY ("cardId")
);
ALTER TABLE "CardDescription" ADD CONSTRAINT "unique_cardId" UNIQUE ("cardId");
CREATE INDEX cardDescIdx ON "CardDescription" ("cardId");

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
  "NAME"        INT     NOT NULL,
  "DESCRIPTION" VARCHAR NULL,
  PRIMARY KEY ("ID")
);