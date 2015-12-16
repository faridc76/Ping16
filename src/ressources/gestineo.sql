

-- --------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `utl_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `utl_matricule` TEXT NOT NULL,
  `utl_password` TEXT NOT NULL,
  `utl_nom` TEXT NOT NULL,
  `utl_prenom` TEXT NOT NULL,
  `utl_numero` TEXT NOT NULL,
  `utl_bureau` TEXT NOT NULL,
  `utl_mail` TEXT NOT NULL,
  `utl_function` TEXT NOT NULL
  
);

-- --------------------------------------------------

--
-- Structure de la table `affaire`
--

CREATE TABLE IF NOT EXISTS `affaire` (
  `afa_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `afa_lieu` TEXT NOT NULL,
  `afa_budget` int(11) NOT NULL,
  `afa_commenditaire` TEXT NOT NULL,
  `afa_resp` int(11) NOT NULL,
  `afa_condu` int(11) NOT NULL,
  `afa_chef` int(11) NOT NULL,
  CONSTRAINT fk_afa_resp_1 FOREIGN KEY (afa_resp) REFERENCES utilisateur (utl_id) ON DELETE SET NULL,
  CONSTRAINT fk_afa_resp_2 FOREIGN KEY (afa_condu) REFERENCES utilisateur (utl_id) ON DELETE SET NULL,
  CONSTRAINT fk_afa_resp_3 FOREIGN KEY (afa_chef) REFERENCES utilisateur (utl_id) ON DELETE SET NULL
);

-- --------------------------------------------------

--
-- Structure de la table `materiel`
--


CREATE TABLE IF NOT EXISTS `materiel` (
  `mat_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `mat_reference` TEXT NOT NULL,
  `mat_fournisseur` int(11) NOT NULL,
  `mat_prix` TEXT NOT NULL
);


-- --------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `cmd_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `cmd_numero` int(11) NOT NULL,
  `cmd_quantite` int(11) NOT NULL,
  `cmd_mat_id` int(11) NOT NULL,
  `cmd_afa_id` int(11) NOT NULL,
  `cmd_utl_id` int(11) NOT NULL,
  CONSTRAINT fk_cmd_mat FOREIGN KEY (cmd_mat_id) REFERENCES materiel (mat_id) ON DELETE SET NULL,
  CONSTRAINT fk_cmd_afa FOREIGN KEY (cmd_afa_id) REFERENCES affaire (afa_id) ON DELETE SET NULL,
  CONSTRAINT fk_cmd_utl FOREIGN KEY (cmd_utl_id) REFERENCES utilisateur (utl_id) ON DELETE SET NULL
);





