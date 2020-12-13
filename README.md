# Pigeon SMS Gateway

It is an application that turns your Android into an SMS Gateway, allowing you to add SMS 
functionality to your software. It connects to a Webservice to retrieve messages to be sent 
(in JSON format) at regular intervals. It also notifies you of delivery status and incoming messages.

## Why?

Commercial SMS APIs are (for most cases) prohibitively expensive. Instead you can use your own phone 
line to send SMS with an Android phone as a gateway.

There are other SMS gateways projects but as far as I could check when this project started, none of 
them can be use to send and receive SMS via HTTP API easily and freely (with no commercial dependencies).

## Install

 ... here link Google Play.

## Settings

 ... here printscreen settings.
 
 ### Send SMS
 - Enable sending: whether the app should read from the API and send messages.
 
 - Send URL: messages will be parsed from this URL, you return a JSON containing message, number and 
 id.
 
 - Interval: the app will check whether there is an incoming message for sending each specific 
 interval in minutes.
 
 - Status URL: once a message is sent, status will be reported to this URL via GET parameters, id 
 and status (SENT, FAILED, DELIVERED).
 
 ### Receive SMS
 
 - Receive URL: Message received will be posted here. If nothing is specified it will skip this action.
 
 ## Baseline
 
 - [SMSHub](https://github.com/juancrescente/SMSHub)