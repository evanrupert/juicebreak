## Inspiration
Having such a sudden transition from an office setting due to the spread of COVID-19 made us realize how much of our creative energy stems from being able to talk to our coworkers about topics that don’t pertain to work. We really wanted to bring back some of that “watercooler conversation” to online platforms, and found that other solutions tend to appear as “mandatory fun”, or can take too much time to set up. We decided to create a solution that is easy to use and is as engaging as possible without detracting from the work environment.

## What it does
Juice Break is a Slack Bot that randomly chooses and posts from a bank of several different types of challenges in a channel, each with randomized prompts in order to ensure that there are no repeats. Some of the current challenges include:

Three-Word Startup! : Given three random words, give a two-sentence description of your best startup idea!
Creative Capitalism!: Given a crazy event, give a two-sentence description of how you would turn it into a business that makes bank?
Faceoff!: Which of the two random character/weapon combinations would win in a fight?
Dumb Debates!: Make a case for your side of the most controversial debates on topics that don’t matter!
The Super Cool Drawing Contest!: Given a random adjective and noun, post your best five-minute drawing!

Team members can then respond to the challenge in a thread, and can “vote” for interesting superlatives using Slack’s built-in reaction system. At the end of the challenge period, Juice Break will tally up the votes and declare the winners! 


## How We built it
We wrote the bot interface from scratch using an http server and client in the Kotlin programming language. We used Ktor with netty as our webserver to listen for requests from the slack events api.  In order to post new messages we used the slack web api with Fuel as an http client.

## Challenges We ran into
The biggest challenge we ran into was keeping track of separate slack threads and making sure the bot only accounted for messages that were posted in it’s own thread.  We solved this by checking the timestamp of the posted message against the parent message of all further messages received. 

## Accomplishments that We're proud of
We are really proud of our system for creating randomized prompts.  We made it so it could be easily extended if in the future we allowed users to easily create their own prompts with random words/phrases to add into the mix by inserting terms like [noun] and [adjective] when writing them.  We are also proud to have built our own framework for writing slack bots in kotlin instead of using a premade slack bot framework.

## What We learned
We learned a lot about the slack api and how it uses http requests to facilitate complex bot creation. We also learned about the top-down design approach and how important it is to break down your larger goals into smaller components in order to create a viable product in a timely manner. 

## What's next for Juice Break
In the future we would love to expand the customizability of juice break so it becomes a platform for fun instead of a hard-coded set of challenges.  We want to make it so that users can add as many prompts as they want, including the ability to easily add randomizers within the prompt.  We also want to add the ability for the user to add their own superlative emojis and descriptions.  

We would like to be able to call on the community to create their own content so that users can download premade challenge packs put together by others.

We would also like to expand and share the framework we created to build Juice Break so that other creators can more quickly overcome the challenges we faced and focus on creating quality content.

Devpost: https://devpost.com/software/juice-break
