I have revised the SaruChess UML!  The semester is over and time is abundant.  The following revisions were made:<BR> <OL> <LI>The packages were folded into the main UML in order to get a better feel for how everything fits together.</LI> <LI>The framework classes were color coded baby blue.</LI> <LI>Added the Transposition Table and History classes in order to strengthen the search engine.</LI> <LI>Added a Move Sorter, but this was a mistake.  It will be removed in the next revision.  Its functionality is inherent in the Evaluation Engine.</LI> <LI>Plugged in some chess-specific classes.</LI> <LI>Added a Movement class as part of the Piece class. Movement will serve to describe how a piece moves.  This class may have to be tightly coupled with the ChessBoard object, but at the moment I have no idea how to get past that.</LI> <LI>Added a Principal variation class.</LI> </OL> <BR> <IMG SRC="./images/GameFramework_rev1.gif"><BR> -m