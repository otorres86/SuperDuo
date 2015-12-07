# SuperDuo

###Obtaining and utilizing API key
  1. Go to http://api.football-data.org/register to get the API key (free)
  2. Once the API key is received, in the application files, go to Football_Scores-master/app/main/src/res/values/strings.xml 
  and insert the received key into the value for "api_key" string

###Required changes
  1. Today's scores can be displayed in a widget (collection widget)
  2. Content descriptions were added for buttons
  3. The app supports layout mirroring

###Extras
  1. Put strings in string.xml file and untranslatable strings have a translatable tag marked to false
  2. A few errors were found, fixed, and called out in comments
  3. Football Scores' widget is of Collection type
  
# Alexandria

###Required Changes
  1. Implemented barcode scanning functionality
  2. Handled the case when searching for a book without an internet connection

###Extras
  1. Barcode scanning functionality does not require the installation of a separate app
  2. Accounted for cases when server is down or invalid data is received
