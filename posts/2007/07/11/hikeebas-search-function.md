[sourcecode lang="c" gist="1003531"]int search(int alpha, int beta, int depth)
{
  int i, j;  //Looping variables
  int evaluation_score;  //The static evaluation
  int a, b;  //Temporary Alpha-beta scores
  BOOLEAN check = FALSE;  //Are we in check?
  BOOLEAN found_legal_move;  //Have we found a legal move?

  /*** Return a score only when we are at a quiet position ***/
  if(!depth)
    return(quiesce(alpha, beta));

  a = alpha;
  b = beta;

  nodes++;
  principal_variation_size[ply] = ply;

  /*** If we are in check then we want to search deeper in the tree ***/
  check = in_check(side);

  if(check)
    depth++;

  generate_legal_moves();

  if(follow_pv)  /* Are we following the principal variation? */
    find_principal_variation();

  found_legal_move = FALSE;

  /*** Loop through the moves ***/
  for(i = move_start[ply]; i < move_end[ply]; i++)
  {
     find_best_move(i);

     /*** If we can't make the best move then we start over ***/
     if(!make_move(move_stack[i].current_move.description))
       continue;

     found_legal_move = TRUE;

     /***
      * Now that we found our move we have to check for the
      * opponent's reply
      ***/
     evaluation_score = -negascout(-b, -a, depth-1);

     /*** Now that the move's been considered take it back ***/
     take_back();

     if((evaluation_score > a) &&
        (evaluation_score < beta) &&
        (i > move_start[ply]) &&
        (depth < max_depth-1))
     {
        a = -negascout(-beta, -evaluation_score, depth-1);
     }

     a = max_int(a, evaluation_score);

     if(a >= beta)
     {
        /***
         * The current move caused a cutoff so we want to increase the
         * history value so that this move gets ordered high next time
         * that we search it
         ***/
         history[move_stack[i].current_move.description.from] \
                [move_stack[i].current_move.description.to] += depth;

         return(a);
      }

      /*** Update the principal variation ***/
      principal_variation[ply][ply] = move_stack[i].current_move;

      for(j = ply + 1; j < principal_variation_size[ply+1]; j++)
        principal_variation[ply][j] = principal_variation[ply+1][j];

      principal_variation_size[ply] = principal_variation_size[ply+1];

      b = a + 1;
    }
  return(a);
}
[/sourcecode]

-m
