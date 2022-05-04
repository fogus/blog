<pre>;
<br />; Library of Babel
<br />; by Fogus
<br />;
<br />
<br />(world (shading gourad flat)
<br />	(camera (position 100 100 20) (lookat 0 0 20))
<br />	(lights
<br />		(light (type model) (ambient 0.5 0.5 0.5 1.0))
<br />		(light (type directional) 
<br />			(ambient 0.5 0.5 0.5 1.0) 
<br />			(diffuse 1.0 1.0 1.0 1.0) 
<br />			(specular 1.0 1.0 1.0 1.0) 
<br />			(position 0.0 0.0 1.0 1.0)
<br />		)
<br />		; Also allows point light
<br />	)
<br />
<br />	(object (name cube1) (type cube)
<br />		(scale 40 40 40)
<br />		(translate 0 40 100)
<br />		(material
<br />			(ambience 0.8 0.8 0.2 1.0)
<br />			(diffusity 0.1 0.5 0.8 1.0)
<br />			(specularity 1.0 1.0 1.0 1.0)
<br />			(emission 0 0 0 1)
<br />			(shininess 1.0)
<br />		}
<br />	)
<br />)</pre>
<br />
<br /><img src="images/babel001.jpg">
<br />-m
<br />