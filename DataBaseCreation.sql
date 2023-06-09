CREATE TABLE utilisateur (
  idUtilisateur int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  mot_de_passe varchar(50),
  nom varchar(50),
  prenom varchar(50),
  email varchar(50)
);
CREATE TABLE etat_projet (
  code_etat varchar(50) PRIMARY KEY,
  libelleEtat varchar(50)
);

CREATE TABLE projet (
  idProjet int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  nom_projet varchar(50),
  codeEtat varchar(50) REFERENCES etat_projet(code_etat),
  idUtilisateur int REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE besoin (
  idBesoin int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  libelleBesoin varchar(50)
);

CREATE TABLE epic (
  idEpic int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  titreEpic varchar(50),
  numero int
);

CREATE TABLE userstory (
  idUserStory int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  acteurUserStory varchar(50),
  titre varchar(50),
  numero int,
  idEpic int REFERENCES epic(idEpic),
  idProjet int REFERENCES projet(idProjet),
  idBesoin int REFERENCES besoin(idBesoin)
);

CREATE TABLE MFC (
  idMfc int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  titreMfc varchar(50),
  idProjet int REFERENCES projet(idProjet)
);

CREATE TABLE Bpmn (
  idBpmn int PRIMARY KEY,
  nomBpmn varchar(50),
  idProjet int REFERENCES projet(idProjet)
);



CREATE TABLE satisfaction (
  idSatisfaction int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  libelleSatisfaction varchar(50),
  idUserStory int REFERENCES userstory(idUserStory)
);

CREATE TABLE acteur_MFC (
  idActeurMFC int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  nomActeurMFC varchar(150)
);

CREATE TABLE acteur_BPMN (
  idActeurBPMN int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  nomActeurBPMN varchar(150)
);

CREATE TABLE fluxMFC (
  idFluxMfc int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  nomFluxMFC varchar(50),
  idMfc int REFERENCES MFC(idMfc),
  idActeurMFC int REFERENCES acteur_MFC(idActeurMFC),
  idActeurMFC_1 int REFERENCES acteur_MFC(idActeurMFC)
);

CREATE TABLE fluxBPMN (
  idFluxBPMN int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  nomFluxBPMN varchar(50),
  idActeurBPMN int REFERENCES acteur_BPMN(idActeurBPMN),
  idActeurBPMN_1 int REFERENCES acteur_BPMN(idActeurBPMN),
  idBpmn int REFERENCES Bpmn(idBpmn)
);

CREATE TABLE correspondance (
  idFluxBPMN int REFERENCES fluxBPMN(idFluxBPMN),
  idFluxMfc int REFERENCES fluxMFC(idFluxMfc),
  coherence varchar(50),
  PRIMARY KEY (idFluxBPMN, idFluxMfc)
);

CREATE TABLE correspondre (
  idActeurMfc int REFERENCES acteur_MFC(idActeurMFC),
  idActeurBPMN int REFERENCES acteur_BPMN(idActeurBPMN),
  correspondance varchar(50),
  PRIMARY KEY (idActeurMfc, idActeurBPMN)
);


