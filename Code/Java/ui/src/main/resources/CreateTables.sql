DROP TABLE IF EXISTS "Edition";
DROP TABLE IF EXISTS "CardDescription";
//Create Edition:

CREATE TABLE PUBLIC."Edition"
(
    "editionId" VARCHAR  NOT NULL,
    "name" VARCHAR  NULL,
    "numOfCards" INT NULL,
    "lang" VARCHAR  NULL,
    PRIMARY KEY ( "editionId" )
);
ALTER TABLE PUBLIC."Edition" ADD CONSTRAINT "unique_editionId" UNIQUE ("editionId");

//Create CardDescription
CREATE TABLE PUBLIC."CardDescription"
(
  "edition" VARCHAR NOT NULL ,
   "type" varchar NOT NULL,
  "subType" varchar NOT NULL ,
  "manaConst" VARCHAR NOT NULL ,
  "convManaConst" INT NOT NULL ,
  "power" INT NOT NULL DEFAULT 0,
  "toughness" INT NOT NULL DEFAULT 0,
  "imageURL" VARCHAR NOT NULL DEFAULT '',
  "cardText" VARCHAR NOT NULL ,
  "flavorText" VARCHAR NOT NULL DEFAULT '',
  "cardNumber" VARCHAR NOT NULL DEFAULT '',
  "artist" VARCHAR NOT NULL DEFAULT '',
  "rarity" INT NOT NULL DEFAULT 0,
  "name" VARCHAR NOT NULL,
  "cardId" varchar NOT NULL AS concat("edition", '_', "cardNumber")
);
ALTER TABLE "CardDescription" ADD CONSTRAINT "unique_cardId" UNIQUE ("cardId");
CREATE INDEX cardDescIdx ON "CardDescription"("cardId");