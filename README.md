# Omnic
This Discord bot posts Overwarch patch notes links into your #Overwatch channel as they're released.

To run, you must set your OMNIC_KEY environmental variable to your bot's api token.

## Invite to your server
If you want this service on your server, you can invite the main **Omnic** bot to your 
server through [this link](https://discordapp.com/oauth2/authorize?client_id=450375495234748416&scope=bot).
Once invited, it will post to your `#overwatch` channel (make sure it exists!) whenever new patch notes are out.


## Prerequisites to Install

* [Apache Maven 3.9+](https://maven.apache.org/download.cgi)
* [Docker](https://docs.docker.com/install/)

## Installing

Before getting started, you will need to [create your bot through Discord](https://discordapp.com/developers/applications/me). 
After getting the credentials for your new bot, export a new environmental variable named `OMNIC_KEY` which 
holds your bot's API token.

After cloning this repository, run this command in the project's root folder: `chmod +x run.sh && ./run.sh`

## Contributing

Thank you for your interest. If you'd like to contribute, please fork the repository and use a feature 
branch. Pull requests are warmly welcome. Alternatively, let me know of any issues you face by filing a 
report in the project's [issue tracker](https://github.com/ChristianLowe/Omnic/issues).

## Licensing

The code in this project is licensed under [the MIT license](https://tldrlegal.com/license/mit-license).

