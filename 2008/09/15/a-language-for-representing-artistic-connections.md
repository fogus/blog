For the past 10 years or so, I have attempted to keep track of the way in which my tastes in art (i.e. literature, music, film, etc..) are influenced.  I initially started off doing this in the Dot language, but quickly found this to be untenable.  I have considered migrating my current stack of thousands of connections to Prolog, but perhaps a simpler, more specific language would be a better option.  This language would of course have to provide a way to define core entities symbolically.

<table callpadding='0' cellspacing='0' class='irisContainer' style='border-collapse: collapse; border-spacing:0'><tbody><tr><td style='margin: 0; padding:0'></td>
<td rowspan='2' class='highlighted output'><pre class="ruby highlighted"><span class="normal">a(<span class="constant">:Philosopher</span>).isa(<span class="constant">:Person</span>)
a(<span class="constant">:Musician</span>).isa(<span class="constant">:Person</span>)
a(<span class="constant">:Friend</span>).isa(<span class="constant">:Person</span>)
a(<span class="constant">:Musician</span>).isa(<span class="constant">:Person</span>)
a(<span class="constant">:Magazine</span>).isa(<span class="constant">:Media</span>)
a(<span class="constant">:Book</span>).isa(<span class="constant">:Media</span>)
</span></pre>
</td></tr><tr><td></td></tr></tbody></table>

Next, it would have to provide a way to track how links between the act of observing art and discovering new links occurs:

<table callpadding='0' cellspacing='0' class='irisContainer' style='border-collapse: collapse; border-spacing:0'><tbody><tr><td style='margin: 0; padding:0'></td>
<td rowspan='2' class='highlighted output'><pre class="ruby highlighted"><span class="normal">reading(<span class="delimiter">"</span><span class="string">Apparat Singles Collection v1</span><span class="delimiter">"</span>).found(<span class="delimiter">"</span><span class="string">The Encyclopedia of Science Fiction</span><span class="delimiter">"</span>)
reading(<span class="delimiter">"</span><span class="string">Apparat Singles Collection v1</span><span class="delimiter">"</span>).found(<span class="delimiter">"</span><span class="string">Doc Savage</span><span class="delimiter">"</span>)
reading(<span class="delimiter">"</span><span class="string">Apparat Singles Collection v1</span><span class="delimiter">"</span>).found(<span class="delimiter">"</span><span class="string">J.G. Ballard</span><span class="delimiter">"</span>).created(<span class="delimiter">"</span><span class="string">Crash</span><span class="delimiter">"</span>)
reading(<span class="delimiter">"</span><span class="string">Apparat Singles Collection v1</span><span class="delimiter">"</span>).found(<span class="delimiter">"</span><span class="string">Alfred Bester</span><span class="delimiter">"</span>).created(<span class="delimiter">"</span><span class="string">The Stars My Destination</span><span class="delimiter">"</span>)
reading(<span class="delimiter">"</span><span class="string">Apparat Singles Collection v1</span><span class="delimiter">"</span>).found(<span class="delimiter">"</span><span class="string">Tom Waits</span><span class="delimiter">"</span>).created(<span class="delimiter">"</span><span class="string">The Mule Variations</span><span class="delimiter">"</span>)
</span></pre>
</td></tr><tr><td></td></tr></tbody></table>

It would also be nice to define the category of an element in-place (this setting would be idempotent?):

<table callpadding='0' cellspacing='0' class='irisContainer' style='border-collapse: collapse; border-spacing:0'><tbody><tr><td style='margin: 0; padding:0'></td>
<td rowspan='2' class='highlighted output'><pre class="ruby highlighted"><span class="normal">reading(<span class="delimiter">"</span><span class="string">Nodame Cantabile</span><span class="delimiter">"</span>).at(<span class="delimiter">'</span><span class="string">2008.09.12</span><span class="delimiter">'</span>).found(<span class="delimiter">"</span><span class="string">Edward Elgar</span><span class="delimiter">"</span>).isa(<span class="constant">:Musician</span>)
</span></pre>
</td></tr><tr><td></td></tr></tbody></table>

Of course, the verbs used to start a link should not be a requirement.  Instead, there should be a way to refer to elements directly and then create links immediately.

<table callpadding='0' cellspacing='0' class='irisContainer' style='border-collapse: collapse; border-spacing:0'><tbody><tr><td style='margin: 0; padding:0'></td>
<td rowspan='2' class='highlighted output'><pre class="ruby highlighted"><span class="normal"><span class="error">$(</span><span class="delimiter">"</span><span class="string">Rob Friesel</span><span class="delimiter">"</span>).isa(<span class="constant">:Friend</span>).recommends(<span class="delimiter">"</span><span class="string">House of Leaves</span><span class="delimiter">"</span>)
<span class="error">$(</span><span class="delimiter">"</span><span class="string">http://www.houseofleaves.com</span><span class="delimiter">"</span>).refers_to(<span class="delimiter">"</span><span class="string">House of Leaves</span><span class="delimiter">"</span>)
<span class="error">$(</span><span class="delimiter">"</span><span class="string">Rob Friesel</span><span class="delimiter">"</span>).recommends(<span class="delimiter">"</span><span class="string">Perdido Street Station</span><span class="delimiter">"</span>)
<span class="error">$(</span><span class="delimiter">"</span><span class="string">Warren Ellis</span><span class="delimiter">"</span>).created(<span class="delimiter">"</span><span class="string">Apparat Singles Collection v1</span><span class="delimiter">"</span>)
<span class="error">$(</span><span class="delimiter">"</span><span class="string">Weird Tales</span><span class="delimiter">"</span>).isa(<span class="constant">:Magazine</span>).recommends(<span class="delimiter">"</span><span class="string">The Brief History of the Dead</span><span class="delimiter">"</span>)
<span class="error">$(</span><span class="delimiter">"</span><span class="string">Matt Bachtell</span><span class="delimiter">"</span>).isa(<span class="constant">:Friend</span>).recommends(<span class="delimiter">"</span><span class="string">H.P. Lovecraft</span><span class="delimiter">"</span>).isa(<span class="constant">:Author</span>)

</span></pre>
</td></tr><tr><td></td></tr></tbody></table>

It would be nice to be able to attach metadata to entities directly:

<table callpadding='0' cellspacing='0' class='irisContainer' style='border-collapse: collapse; border-spacing:0'><tbody><tr><td style='margin: 0; padding:0'></td>
<td rowspan='2' class='highlighted output'><pre class="ruby highlighted"><span class="normal"><span class="error">$(</span><span class="delimiter">"</span><span class="string">RESTful Web Services</span><span class="delimiter">"</span>).attach {<span class="delimiter">"</span><span class="string">isbn</span><span class="delimiter">"</span> =&gt; <span class="delimiter">"</span><span class="string">0596529260</span><span class="delimiter">"</span>}
</span></pre>
</td></tr><tr><td></td></tr></tbody></table>

Sometimes, one piece of art is influenced by another:

<table callpadding='0' cellspacing='0' class='irisContainer' style='border-collapse: collapse; border-spacing:0'><tbody><tr><td style='margin: 0; padding:0'></td>
<td rowspan='2' class='highlighted output'><pre class="ruby highlighted"><span class="normal"><span class="error">$(</span><span class="delimiter">"</span><span class="string">Sword of Shannara</span><span class="delimiter">"</span>).steals(<span class="delimiter">"</span><span class="string">Lord of the Rings</span><span class="delimiter">"</span>)
<span class="error">$(</span><span class="delimiter">"</span><span class="string">Neil Stephenson</span><span class="delimiter">"</span>).influences(<span class="delimiter">"</span><span class="string">Rob Friesel</span><span class="delimiter">"</span>)
