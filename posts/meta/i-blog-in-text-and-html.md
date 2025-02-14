---
title: "I Blog in Text, You Read in Text || HTML"
author: "Fogus"
date: "2025.02.14"
---

Inspired by ["I blog with raw txt"](https://misc.l3m.in/txt/raw_txt.txt) and ["I blog in raw HTML"](https://devpoga.org/i-blog-with-raw-html/)...

The blog you're reading was rendered at some point in the past as HTML (maybe) but it was written in text with a dash of Markdown flavor (maybe). However, if you'd like to read the original text version, then go up to the location bar in the browswer and replace the "/" or "html" part of the URL with ".md" and hit enter.[^md]  I recently replaced a borked Wordpress instance that I maintained for 10+ years with a Bash script that calls out to Pandoc and renders my text as HTML while also inserting some fragments along the way for header and footer glue.

What I do is write a new post into a subdirectory in my blog directory and run:[^maybe]

    find ./posts/<thedir> -name "*.md" -exec sh -c 'pandoc -s \\
	--css https://blog.fogus.me/styles/reset.css \\
	--css https://blog.fogus.me/styles/index.css \\
	-A https://blog.fogus.me/styles/footer.html \\
	-B https://blog.fogus.me/styles/header.html \\
	-H styles/meta.html -o "${1%.md}.html" "$1"' _ {} \;

I then run a fragment of Clojure code that slurps that filename and title from the new file and adds a new entry into an index.md file and a feed.xml file. I then run a similar command as above on the index.md file and upload all of the new files to my server.

At serve time I have a rewrite rule on a request URL and tries to find an HTML file at that address. If one exists then it serves it. If it doesn't then it tries to find a .md file and serve it. If it doesn't find that then it looks for a .txt file or a .org file as a last resort. This seems a bit fiddly and I agree that it is, but it gives me the control that I want and is 100x times less fiddly than the Wordpress instance turned out to be -- and I consider that a win.

-fogus

[^md]: If that didn't work then try ".txt" or ".org".

[^maybe]: The command for and org-mode file is slightly different and there is no such command for raw text files.
