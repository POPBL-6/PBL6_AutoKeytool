# PBL6_AutoKeytool
Create multiple certificates

This program creates multiple certificates that can be used with the default cipher in the
<a href=https://github.com/POPBL-6/broker>Broker</a> and <a href=https://github.com/POPBL-6/middleware>Middleware</a>.

This is just an example, feel free to analyze the code and modify its behavior to fit your needs
(automating the keytool usage).

## What it does
Running AutoKeytool.main(String[] args) (args ignored) will make this application spawn keystores as specified
in <a href=https://github.com/POPBL-6/PBL6_AutoKeytool/blob/master/AutoKeytool.ini>AutoKeytool.ini</a>.

### AutoKeytool.ini file format
This file must contain any number of lines with the following format:

`<A>;<B>;<C>`

Where `<A>` is the name of the certificate (`CN=<A>`), `<B>` is the password for both the keystore and its 
certificates (should later configure`-kp <B>` in either `SSLListener` or `PSPortSSL`), and `<C>` is the generated
keystore file path and name (should later configure`-k <C> -t <C>` in either `SSLListener` or `PSPortSSL`).

## How this works with the Broker/Middleware
Appart from generating the keys, this application assumes the first certificate in AutoKeytool.ini is for
the Broker, and all the others are for Middleware clients.

Therefore, it imports all the clients public keys to the first (Broker) keystore, and the first (Broker) public key
will be imported to all the other keystores.

So if you have this in <a href=https://github.com/POPBL-6/PBL6_AutoKeytool/blob/master/AutoKeytool.ini>AutoKeytool.ini</a>:

    Broker;snowflake;certs/broker.jks
    Client1;snowflake;certs/client1.jks
    Client2;snowflake;certs/client2.jks
    Client3;snowflake;certs/client3.jks
    Client4;snowflake;certs/client4.jks
    
This application will create the certs folder and then put broker.jks, client1.jks, client2.jks...

Where broker.jks will have ALL the public keys and the other keystores will have the public key from the
broker.jks certificate.

Then, if you copy broker.jks to the Broker:

    SSLListener -k broker.jks -t broker.jks -kp snowflake
    
You will be trusting any of the previously created certificates.

In a Middleware application, for example, if you copy client1.jks:

    PSPortSSL -k client1.jks -t client1.jks -kp snowflake
    
The Middleware application should be able to validate the broker (since its PK is in client1.jks), 
and the broker should be able to validate the Middleware application (since its PK is in broker.jks).
