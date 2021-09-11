# Photo Album Printer

This is a basic Grails 3 application that fetches some data from a given URL and prints it to the console.

## Startup

The application can be interacted with in a few ways:

First, it will perform its primary function of fetching and printing photos immediately on start. Simply download the repo on a machine with Grails 3 setup and run the command `grails run-app` with the command line tool of your choice. For Mac, I recommend Hyper; for PC I suggest PowerShell.

Second, once the application is running, there is an endpoint that can be found at `http://localhost:8080/photos` that will perform the same operation on demand.

Lastly, this application uses a ton of default Grails boilerplate. As a result, when the application launches, Grails will provide a GUI to view an overview of the app (artefacts, plugins, etc.). From this view, you may select `lt.photo.album.PhotoController` under the <b>Available Controllers</b> header.

## Overview

I was operating under some strict, family-imposed time constraints with this project. As a result, I decided to use the framework and language with which I am most comfortable &mdash; despite those tools generally being most well-suited for REST clients, they are also designed to be tremendously easy to stand up. Grails prioritizes convention over configuration; as a result, getting a working app that is straightforward to test and has an intuitive layout is quite simple.

Typically, I would invest significantly more time in testing the code I write. This provides a few commits to demonstrate a common pattern I'd use under ordinary circumstances. I believe in a professional setting it's important to provide estimates that allow for adequate time to uphold best practices. However, this was a nice experiment and I had outside forces impacting the time allotted.

One particular area for further testing would be the ApiClientService. Because it acts as a boundary point between code I own and code I don't, it's critical to integration test from top to bottom.

## Structure

The app itself follows a really simple structure. Grails enforces separation of responsibility on a macro level via autowiring classes placed in given directories based on their names. However, that's obviously not adequate in real life (otherwise we'd have a nice single Service/Controller/Domain). As a result, I split my responsibilities like so:

1. Open and manage the connection to a third party API
2. Operations on the Photo class, including initiating fetching/logging and normalization of data
3. Logging the data

I felt these boundaries were intuitive, and believe with additional time they'd provide seams that are easy to test and maintain at a larger scale.

## Operations

To test the application, run `grails test-app -unit` in the command line tool of your choice from the root directory.

To run the application, run `grails run-app` in the command line tool of your choice from the root directory.