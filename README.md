# Bot Discord — Ylan Dessenne
> TP 1 — Java Avancé B2 | Ynov Toulouse

Bot Discord développé en Java avec la librairie JDA, capable de répondre à des commandes, interagir avec les membres d'un serveur et consommer des APIs externes.

---

## Stack technique

- **Java 21+**
- **JDA 5.x** — Java Discord API
- **Maven** — gestion des dépendances
- **dotenv-java** — chargement des variables d'environnement
- **org.json** — parsing JSON

---

## Installation & Lancement

### 1. Prérequis
- Java 21 ou supérieur installé
- Un bot Discord créé sur [discord.com/developers/applications](https://discord.com/developers/applications)

### 2. Configuration
Créer un fichier `.env` à la racine du projet :
```
TOKEN=ton_token_discord_ici
```

> ⚠️ Ne jamais commiter ce fichier sur un dépôt public !

### 3. Lancer le bot
Ouvrir le projet dans IntelliJ IDEA et lancer `Main.java`.

Le bot passera en ligne sur votre serveur Discord.

---

## Commandes disponibles

| Commande | Description                                                    |
|---|----------------------------------------------------------------|
| `!ping` | Répond avec un message Pong !                                  |
| `!help` | Affiche la liste de toutes les commandes disponibles           |
| `!joke` | Récupère et affiche une blague aléatoire depuis une API externe |
| `!poll <question> \| <option1> \| <option2>` | Crée un sondage natif Discord                                  |
| `!setprefix <nouveau_préfixe>` | Change le préfixe du bot (persistant)                          |


---

## Architecture du projet

```
discord-bot/
├── src/main/java/ovh/dessenne/ylan/
│   ├── Main.java                  # Point d'entrée, initialisation du bot
│   ├── BotConfig.java             # Token, préfixe, chargement/sauvegarde config
│   ├── command/
│   │   ├── ICommand.java          # Interface commune à toutes les commandes
│   │   ├── CommandManager.java    # Enregistrement et dispatch des commandes
│   │   ├── PingCommand.java
│   │   ├── HelpCommand.java
│   │   ├── JokeCommand.java
│   │   ├── PollCommand.java
│   │   └── SetPrefixCommand.java
│   ├── listener/
│   │   └── CommandListener.java   # Écoute les messages Discord
│   └── util/
│       └── EmbedHelper.java       # Helper pour les messages embed
├── .env                           # Token (non commité)
├── config.json                    # Préfixe sauvegardé (non commité)
├── .gitignore
└── pom.xml
```

---

## Concepts mis en oeuvre

- **Command Pattern** — chaque commande est une classe indépendante implémentant `ICommand`
- **Réflexion Java** — le `CommandManager` détecte et enregistre automatiquement toutes les commandes du package sans configuration manuelle
- **Événements asynchrones** — gestion des messages via `ListenerAdapter` de JDA
- **Consommation d'API REST** — appel à `official-joke-api.appspot.com` avec `HttpClient`
- **Persistance JSON** — sauvegarde du préfixe dans `config.json` via `org.json`

---

## Liens utiles
- [Documentation JDA](https://ci.dv8tion.net/job/JDA/javadoc/)
- [Discord Developer Portal](https://discord.com/developers/applications)
- [API de blagues](https://official-joke-api.appspot.com/)
- [Guide de développement de bots Discord](https://discordjs.guide/)

# Lien GitHub
Le code source de ce projet est disponible sur GitHub : [Discord Bot - Ylan Dessenne](https://github.com/Torolgo/discord-bot)

## Contact

Pour toute question ou suggestion, n'hésitez pas à me contacter !

### Ylan DESSENE
- **Academy Email** : [ylan.dessenne@ynov.com](mailto:ylan.dessenne@ynov.com)
- **LinkedIn** : [ylan DESSENNE](https://linkedin.com/in/dessenne-ylan)
- **GitHub** : [Torolgo](https://github.com/Torolgo)

---

## License
Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.