# GCM data helper
Transfer custom data throught Google Cloud Messaging (GCM) to the devices.
GCM normally transfer data in a bucket, a map. I customize the map to support serialization framework (Fasterxml).
So you can send a JSON object, or Java graph to the device. 
Java graph will be serialized in the JSON format, defined with data and type of object.

## Client
Android client handle messages, automatically transfer messages to the custom data.
Override JsonGcmListenerService#consumeMessage to consume DTOs.

## Server
GcmClient send message by Google Http-client.
I choose Google HTTP client, because it can run very well at GAE, Android, SE and other environments.

### Dependecies
- Fasterxml 2.7.3
- Resteasy 3.0.16
- Logback 1.1.7
- Google Http-client 1.21

## Okey, how can use it?
Prepare your custom project, with 3 modules.
Create one Android module and one module for Server side.
Create another "domain" module. Put all data-domain-object (DTO) to this module.
Import domain module to other modules. All DOTs will be automatically transferred from server to the devices.
