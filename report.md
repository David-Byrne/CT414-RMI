## Implementation
The system is split into a server component and a client component, which
communicate using RMI. When the server is started up, it generates some fake
student and assignment data to test with. In the real world, this would be
read from some sort of database instead. The in-memory hardcoded examples
are enough to test the systems capabilities for now however.

When a student logs in, their credentials are validated against an in memory
list (would be hashed and checked against a database in the real world).
Assuming their details were correct, a session object is created on the
server for them. They're given a token that identifies the appropriate
session and this is used to authenticate all future requests from them.

The student can see a list of all available assessments. This is done by the
client calling a `getAvailableSummary` method on the remote ExamEngine
object. This creates a list of Assessment objects, serialises it, and sends
it over the network to the client. The client displays the assessments and
allows the user to interact with them

If a user opens an assignment, they're presented with the questions and
what answers they entered last (or default options if they haven't opened
it yet). They can change the selected answers which modifies the assessment
object stored on the client. Once they submit the assessment, it's
serialised and sent back to the server over the network. The server finds
the previous copy of the assessment it was storing and overwrites it with
this updated version. This change would be persisted to the database in a
real world implementation but is just stored in memory in our
implementation.

## Testing
>TODO
