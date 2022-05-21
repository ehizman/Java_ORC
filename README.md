# Object Recognition With Google Cloud Vision API, Twilio, and Java

This project integrates object and text recognition technology provided by [Google Cloud Vision](https://cloud.google.com/vision) into a Java Spring Boot application. The Google Cloud Vision API is used to analyse images and extract labels, find and read printed text from images sent over a private WhatsApp Sandbox created using the [Twilio WhatsApp API](https://www.twilio.com/whatsapp).

To run the application:
1. Create a free [Twilio account](https://www.twilio.com/referral/ePf72t).
2. Create a free [Google Cloud Vision Developer account](https://console.cloud.google.com/home/dashboard). Don't forget to add your Billing Information.
3. Create a ngrok account, [download](https://ngrok.com/download) and install the ngrok client on your computer.
4. Start your ngrok agent by running `ngrok http 8080` in your terminal.
5. Clone the project on your favourite Integrated Development Environment (IDE).
6. Install dependencies by running the following command: `mvn install`.
7. Run the project on your IDE or run the following command on your terminal: `java -jar ./target/customer-service.jar`. You must have either the Java Runtime Environment (JRE) or the Java Development Kit (JDK) installed on your machine.
Click [here](https://www.oracle.com/java/technologies/downloads/) to find a JRE or JDK version that suits your machine.

