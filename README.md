# CryptoPass
This project generates a password from a simple word or Character, which is unique for everyone.
It serves practically the same purpose as a password manager, which is:

You don't need to remember your passwords.

# How is CryptoPass different?
A password manager stores your passwords somewhere secure in a database on a server.
But this means that it is connected to the internet and thus provides a target for hackers.

Don't get me wrong, you can be attacked too, but it's less likely that hackers will come after you.
And besides, with CryptoPass you don't store passwords.

# How does it work?
"Well,..." you're asking yourself "...if it doesn't store passwords, then what does it store?".
And that is a great question.

CryptoPass stores your UUPK (User Unique Password Key) on your device.
It then uses your UUPK and a string of characters which you enter to calculates the passwords from zero.
But don't worry it is generated in an instant and is unique for every word you enter.

It may be possible, though I'm not sure that once in a few hundred billion words, which you enter,
there could possibly be a collision of words. Which means that two passwords that come out will be the same.
But I don't think you would even need that many passwords, if you do let me know. LOL

# Download
To download CryptoPass, go to releases on the right and then select the latest release.
1. Now download the CryptoPass.zip (You don't need those other two files they are for the source code).

2. Go to where you installed the .zip folder and extract it.

3. When extracted you will see three files:
   - The .jar file is the executable one
   - The setup.bat sets up CryptoPass for you to be ready to work. It moves your files to the %userprofile%
     into a folder called CryptoPass. It also creates a shortcut on your desktop and sets a Hotkey for it.
     
     HotKey: CTRL + SHIFT + P
     
   - The uninstall.bat is pretty self-explanatory innit?
     But note that uninstall.bat only works if you have set it up with setup.bat before.
     
And also you will need to have Java 16 installed on your computer and set is as an environment variable.
You will find a lot of tutorials on that online. I will meanwhile try to write a batch file that handles that
in a future release.
     
# And now have fun with CryptoPass!!!
