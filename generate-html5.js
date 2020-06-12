/* adopted from https://github.com/utkarshkukreti/purescript-hedwig/blob/32016433b98a78e11cdd8f6ad78305f78856081b/Rakefile */

function genTags() {
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

function genAttributes() {
  // generated from jsx.d.ts of preact
  // omit `style`, `class` and `className` as we special case them
  const data = `
accept:string;acceptCharset:string;accessKey:string;action:string;allowFullScreen:boolean;
allowTransparency:boolean;alt:string;as:string;async:boolean;autoComplete:string;autoCorrect:string;
autofocus:boolean;autoFocus:boolean;autoPlay:boolean;capture:boolean;cellPadding:number|string;
cellSpacing:number|string;charSet:string;challenge:string;checked:boolean;cols:number;colSpan:number;
content:string;contentEditable:boolean;contextMenu:string;controls:boolean;controlsList:string;
coords:string;crossOrigin:string;data:string;dateTime:string;default:boolean;defer:boolean;
dir:string;disabled:boolean;disableRemotePlayback:boolean;download:string|boolean;draggable:boolean;
encType:string;form:string;formAction:string;formEncType:string;formMethod:string;formNoValidate:boolean;
formTarget:string;frameBorder:number|string;headers:string;height:number|string;hidden:boolean;high:number;
href:string;hrefLang:string;for_:string;htmlFor:string;httpEquiv:string;icon:string;id:string;inputMode:string;
integrity:string;is:string;keyParams:string;keyType:string;kind:string;label:string;lang:string;list:string;
loading:string;loop:boolean;low:number;manifest:string;marginHeight:number;marginWidth:number;
max:number|string;maxLength:number;media:string;mediaGroup:string;method:string;min:number|string;
minLength:number;multiple:boolean;muted:boolean;name:string;nonce:string;noValidate:boolean;open:boolean;
optimum:number;pattern:string;placeholder:string;playsInline:boolean;poster:string;preload:string;
radioGroup:string;readOnly:boolean;rel:string;required:boolean;role:string;rows:number;rowSpan:number;
sandbox:string;scope:string;scoped:boolean;scrolling:string;seamless:boolean;selected:boolean;shape:string;
size:number;sizes:string;slot:string;span:number;spellcheck:boolean;src:string;srcset:string;srcDoc:string;
srcLang:string;srcSet:string;start:number;step:number|string;summary:string;tabIndex:number;target:string;
title:string;type_:string;useMap:string;value:string|number;volume:string|number;width:number|string;
wmode:string;wrap:string;about:string;datatype:string;inlist:string;prefix:string;property:string;resource:string;
typeof:string;vocab:string;itemProp:string;itemScope:boolean;itemType:string;itemID:string;itemRef:string;
  `.trim().split(/[;]/).map(val => val.trim()).filter(val => val !== '');

  // console.log(data);

  function getTypeOf(tsType, index) {
    switch (tsType) {
      case 'string':
        return 'String';
      case 'number':
        return 'Double';
      case 'boolean':
        return 'Boolean';
      default:
        return `T${index}`; // bail
    }
  }

  function makeSpecialSubclass(map, ident, types) {
    const classIdent = titleCase(ident);
    const primaryType = types[0];
    map.subclasses.push(`  class ${classIdent}Name extends AttributeName[${primaryType}]("${ident}") {`);
    types.slice(1).forEach(ty => {
      map.subclasses.push(
        `    def create[F](value: ${ty}): Attribute[F] = new Property(name, value)`,
        `    def := [F](value: ${ty}): Attribute[F] = create(value)`
      );
    });
    map.subclasses.push('  }');
    map.subclasses.push('');

    map.decls.push(
      `  lazy val ${ident}: ${classIdent}Name = new ${classIdent}Name`,
      ''
    );
  }

  const blocks = data.reduce((map, expr) => {
    let [ident, types] = expr.split(':');
    try {
      types = types.split('|').map((s, i) => getTypeOf(s, i));
      
      if (types.length > 1) {
        makeSpecialSubclass(map, ident, types);
      } else {
        map.decls.push(
          `  lazy val ${ident} : AttributeName[${types[0]}] = attr[${types[0]}]("${ident}")`
        );
      }
    } catch (err) {
      console.error({ err, types, ident });
      process.exit(99)
    }

    return map;
  }, { subclasses: [], decls: [] });

  const code = blocks.subclasses.join('\n') + '\n' + blocks.decls.join('\n');

  return `
package com.github.thisisvesca
package virtualdom

import VirtualDom._

// GENERATED CODE ... DO NOT EDIT MANUALLY

trait StandardAttributeProvider extends AttributeProvider {
${code}
}
  `.trim();
}

function titleCase(text) {
  return text[0].toUpperCase() + text.substring(1);
}

function upperCamel(ident) {
  if (ident.length === 1) {
    return titleCase(ident[0]);
  } else {
    return ident.map(titleCase).join('');
  }
}

const generator = {
  genTags,
  genEvents,
  genAttributes
};

const cmd = process.argv[2];
if (!cmd || !generator[cmd]) {
  console.error(`usage: node generate-html5.js <${Object.keys(generator)}>`);
  process.exit(1);
} else {
  console.log(generator[cmd]());
}


