CREATE SCHEMA `tasks` ;

CREATE TABLE `tasks`.`usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `nomeUsuario` VARCHAR(100) NOT NULL,
  `emailUsuario` VARCHAR(100) NOT NULL,
  `senhaUsuario` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE INDEX `emailUsuario_UNIQUE` (`emailUsuario` ASC)
);
  
CREATE TABLE `tasks`.`tarefas` (
  `idTarefas` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(150) NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`idTarefas`),
  INDEX `tarefaUsuario_idx` (`usuario` ASC),
  CONSTRAINT `tarefaUsuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `tasks`.`usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
