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

function genEvents() {
  const data = JSON.parse(`
{
  "image": "load error",
  "clipboard": "copy cut paste",
  "composition": "composition-end composition-start composition-update",
  "details": "toggle",
  "focus": "focus blur",
  "form": "change input search submit invalid reset form-data",
  "keyboard": "key-down key-press key-up",
  "media": "abort can-play can-play-through duration-change emptied encrypted ended loaded-data loaded-metadata load-start pause play playing progress rate-change seeked seeking stalled suspend time-update volume-change waiting",
  "mouse": "click context-menu dbl-click mouse-down mouse-enter mouse-leave mouse-move mouse-out mouse-over mouse-up",
  "drag": "drag drag-end drag-enter drag-exit drag-leave drag-over drag-start drop",
  "select": "select",
  "touch": "touch-cancel touch-end touch-move touch-start",
  "pointer": "pointer-over pointer-enter pointer-down pointer-move pointer-up pointer-cancel pointer-out pointer-leave got-pointer-capture lost-pointer-capture",
  "ui": "scroll",
  "wheel": "wheel",
  "animation": "animation-start animation-end animation-iteration",
  "transition": "transition-end"
}`);
  function upperCamel(ident) {
    const toTitle = text => text[0].toUpperCase() + text.substring(1);
    if (ident.length === 1) {
      return toTitle(ident[0]);
    } else {
      return ident.map(toTitle).join('');
    }
  }

  function getEventType(type) {
    switch (type) {
      case 'clipboard':
        return 'ClipboardEvent';
      case 'composition':
        return 'CompositionEvent';
      case 'focus':
        return 'FocusEvent';
      case 'keyboard':
        return 'KeyboardEvent';
      case 'drag':
        return 'DragEvent';
      case 'mouse':
        return 'MouseEvent';
      case 'touch':
        return 'TouchEvent';
      case 'pointer':
        return 'PointerEvent';
      case 'ui':
        return 'UIEvent';
      case 'wheel':
        return 'WheelEvent';
      case 'animation':
        return 'AnimationEvent';
      case 'transition':
        return 'TransitionEvent';
      default:
        return 'Event';
    }
  }
  
  const names = Object.keys(data);
  const code = names.reduce((blocks, name) => {
    const events = data[name].split(' ');
    events.sort();
    const eventType = getEventType(name);
    blocks.push('');
    blocks.push(`  // ${upperCamel([name])} events`);
    events.forEach(key => {
      const ident = upperCamel(key.split('-'));
      blocks.push(`  lazy val on${ident} : EventName[${eventType}] = event[${eventType}]("${key}")`);
    });
    return blocks;
  }, []);

  return `
package com.github.thisisvesca
package html

import com.github.thisisvesca.virtualdom._
import org.scalajs.dom._

// GENERATED CODE ... DO NOT EDIT MANUALLY

trait GlobalEventProvider extends EventProvider {
${code.join('\n')}
}
  `.trim();
}

const generator = {
  genTagFactory,
  genEvents
};

const cmd = process.argv[2];
if (!cmd || !generator[cmd]) {
  console.error(`usage: node generate-html5.js <${Object.keys(generator)}>`);
  process.exit(1);
} else {
  console.log(generator[cmd]());
}


