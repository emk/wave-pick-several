Pick Several
============

Pick Several is a Google Wave gadget.  It implements approval voting (i.e.,
vote for as many choices as you want, and the choice with the most votes
wins).

Pick Several is written using GWT, which feels a bit heavyweight for this
sort of application.  However, GWT did make the GUI programming very easy.

The architecture of Pick Several was inspired by the Model-View-Presenter +
Event Bus architecture recommended by the Google AdWords team, but without
a full separation between the presenter and the view.  See:

http://code.google.com/events/io/2009/sessions/GoogleWebToolkitBestPractices.html