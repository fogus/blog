    Request req = new Request();
    
    req.where.has( _name, Operator.EQUALS, "billy")
      .where.not( _age, Operator.LESS_THAN, 30)
      .sortBy( _name, <comparator>)
      .limit( 10);
    
    Results<Person> = Dbi.process( req, Person.class);

-m