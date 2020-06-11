/* adopted from https://github.com/utkarshkukreti/purescript-hedwig/blob/32016433b98a78e11cdd8f6ad78305f78856081b/Rakefile */

function genTagFactory() {
  const data = `
a abbr address article aside audio b bdi bdo blockquote br button canvas
caption cite code col colgroup datalist dd del details dfn div dl dt em
embed fieldset figcaption figure footer form h1 h2 h3 h4 h5 h6 header hr
i iframe img input ins kbd label legend li main_ map mark math menu
menuitem meter nav node object_ ol optgroup option output p param pre
progress q rp rt ruby s samp section select small source span strong sub
summary sup table tbody td textarea tfoot th thead time tr track u
ul var_ video wbr
  `.trim().split(/\s+/);
  data.sort();

  const body = data.reduce((blocks, name) => {
    const literal = name.replace(/_/g, '');
    blocks.push(`  lazy val ${name} : Builder = builder("${literal}")`);
    return blocks;
  }, []);

  const code = body.join('\n');

  return `
package com.github.thisisvesca
package virtualdom

// GENERATED CODE ... DO NOT EDIT MANUALLY

trait HtmlFactory extends TagFactory {
${code}
}
  `.trim();
}

console.log(genTagFactory());
