package com.github.thisisvesca
package html

import com.github.thisisvesca.virtualdom._
import org.scalajs.dom._

// GENERATED CODE ... DO NOT EDIT MANUALLY

trait GlobalEventProvider extends EventProvider {

  // Image events
  lazy val onError: EventName[Event] = event[Event]("error")
  lazy val onLoad: EventName[Event]  = event[Event]("load")

  // Clipboard events
  lazy val onCopy: EventName[ClipboardEvent]  = event[ClipboardEvent]("copy")
  lazy val onCut: EventName[ClipboardEvent]   = event[ClipboardEvent]("cut")
  lazy val onPaste: EventName[ClipboardEvent] = event[ClipboardEvent]("paste")

  // Composition events
  lazy val onCompositionEnd: EventName[CompositionEvent] =
    event[CompositionEvent]("composition-end")
  lazy val onCompositionStart: EventName[CompositionEvent] =
    event[CompositionEvent]("composition-start")
  lazy val onCompositionUpdate: EventName[CompositionEvent] =
    event[CompositionEvent]("composition-update")

  // Details events
  lazy val onToggle: EventName[Event] = event[Event]("toggle")

  // Focus events
  lazy val onBlur: EventName[FocusEvent]  = event[FocusEvent]("blur")
  lazy val onFocus: EventName[FocusEvent] = event[FocusEvent]("focus")

  // Form events
  lazy val onChange: EventName[Event]   = event[Event]("change")
  lazy val onFormData: EventName[Event] = event[Event]("form-data")
  lazy val onInput: EventName[Event]    = event[Event]("input")
  lazy val onInvalid: EventName[Event]  = event[Event]("invalid")
  lazy val onReset: EventName[Event]    = event[Event]("reset")
  lazy val onSearch: EventName[Event]   = event[Event]("search")
  lazy val onSubmit: EventName[Event]   = event[Event]("submit")

  // Keyboard events
  lazy val onKeyDown: EventName[KeyboardEvent]  = event[KeyboardEvent]("key-down")
  lazy val onKeyPress: EventName[KeyboardEvent] = event[KeyboardEvent]("key-press")
  lazy val onKeyUp: EventName[KeyboardEvent]    = event[KeyboardEvent]("key-up")

  // Media events
  lazy val onAbort: EventName[Event]          = event[Event]("abort")
  lazy val onCanPlay: EventName[Event]        = event[Event]("can-play")
  lazy val onCanPlayThrough: EventName[Event] = event[Event]("can-play-through")
  lazy val onDurationChange: EventName[Event] = event[Event]("duration-change")
  lazy val onEmptied: EventName[Event]        = event[Event]("emptied")
  lazy val onEncrypted: EventName[Event]      = event[Event]("encrypted")
  lazy val onEnded: EventName[Event]          = event[Event]("ended")
  lazy val onLoadStart: EventName[Event]      = event[Event]("load-start")
  lazy val onLoadedData: EventName[Event]     = event[Event]("loaded-data")
  lazy val onLoadedMetadata: EventName[Event] = event[Event]("loaded-metadata")
  lazy val onPause: EventName[Event]          = event[Event]("pause")
  lazy val onPlay: EventName[Event]           = event[Event]("play")
  lazy val onPlaying: EventName[Event]        = event[Event]("playing")
  lazy val onProgress: EventName[Event]       = event[Event]("progress")
  lazy val onRateChange: EventName[Event]     = event[Event]("rate-change")
  lazy val onSeeked: EventName[Event]         = event[Event]("seeked")
  lazy val onSeeking: EventName[Event]        = event[Event]("seeking")
  lazy val onStalled: EventName[Event]        = event[Event]("stalled")
  lazy val onSuspend: EventName[Event]        = event[Event]("suspend")
  lazy val onTimeUpdate: EventName[Event]     = event[Event]("time-update")
  lazy val onVolumeChange: EventName[Event]   = event[Event]("volume-change")
  lazy val onWaiting: EventName[Event]        = event[Event]("waiting")

  // Mouse events
  lazy val onClick: EventName[MouseEvent]       = event[MouseEvent]("click")
  lazy val onContextMenu: EventName[MouseEvent] = event[MouseEvent]("context-menu")
  lazy val onDblClick: EventName[MouseEvent]    = event[MouseEvent]("dbl-click")
  lazy val onMouseDown: EventName[MouseEvent]   = event[MouseEvent]("mouse-down")
  lazy val onMouseEnter: EventName[MouseEvent]  = event[MouseEvent]("mouse-enter")
  lazy val onMouseLeave: EventName[MouseEvent]  = event[MouseEvent]("mouse-leave")
  lazy val onMouseMove: EventName[MouseEvent]   = event[MouseEvent]("mouse-move")
  lazy val onMouseOut: EventName[MouseEvent]    = event[MouseEvent]("mouse-out")
  lazy val onMouseOver: EventName[MouseEvent]   = event[MouseEvent]("mouse-over")
  lazy val onMouseUp: EventName[MouseEvent]     = event[MouseEvent]("mouse-up")

  // Drag events
  lazy val onDrag: EventName[DragEvent]      = event[DragEvent]("drag")
  lazy val onDragEnd: EventName[DragEvent]   = event[DragEvent]("drag-end")
  lazy val onDragEnter: EventName[DragEvent] = event[DragEvent]("drag-enter")
  lazy val onDragExit: EventName[DragEvent]  = event[DragEvent]("drag-exit")
  lazy val onDragLeave: EventName[DragEvent] = event[DragEvent]("drag-leave")
  lazy val onDragOver: EventName[DragEvent]  = event[DragEvent]("drag-over")
  lazy val onDragStart: EventName[DragEvent] = event[DragEvent]("drag-start")
  lazy val onDrop: EventName[DragEvent]      = event[DragEvent]("drop")

  // Select events
  lazy val onSelect: EventName[Event] = event[Event]("select")

  // Touch events
  lazy val onTouchCancel: EventName[TouchEvent] = event[TouchEvent]("touch-cancel")
  lazy val onTouchEnd: EventName[TouchEvent]    = event[TouchEvent]("touch-end")
  lazy val onTouchMove: EventName[TouchEvent]   = event[TouchEvent]("touch-move")
  lazy val onTouchStart: EventName[TouchEvent]  = event[TouchEvent]("touch-start")

  // Pointer events
  lazy val onGotPointerCapture: EventName[PointerEvent] = event[PointerEvent]("got-pointer-capture")
  lazy val onLostPointerCapture: EventName[PointerEvent] =
    event[PointerEvent]("lost-pointer-capture")
  lazy val onPointerCancel: EventName[PointerEvent] = event[PointerEvent]("pointer-cancel")
  lazy val onPointerDown: EventName[PointerEvent]   = event[PointerEvent]("pointer-down")
  lazy val onPointerEnter: EventName[PointerEvent]  = event[PointerEvent]("pointer-enter")
  lazy val onPointerLeave: EventName[PointerEvent]  = event[PointerEvent]("pointer-leave")
  lazy val onPointerMove: EventName[PointerEvent]   = event[PointerEvent]("pointer-move")
  lazy val onPointerOut: EventName[PointerEvent]    = event[PointerEvent]("pointer-out")
  lazy val onPointerOver: EventName[PointerEvent]   = event[PointerEvent]("pointer-over")
  lazy val onPointerUp: EventName[PointerEvent]     = event[PointerEvent]("pointer-up")

  // Ui events
  lazy val onScroll: EventName[UIEvent] = event[UIEvent]("scroll")

  // Wheel events
  lazy val onWheel: EventName[WheelEvent] = event[WheelEvent]("wheel")

  // Animation events
  lazy val onAnimationEnd: EventName[AnimationEvent] = event[AnimationEvent]("animation-end")
  lazy val onAnimationIteration: EventName[AnimationEvent] =
    event[AnimationEvent]("animation-iteration")
  lazy val onAnimationStart: EventName[AnimationEvent] = event[AnimationEvent]("animation-start")

  // Transition events
  lazy val onTransitionEnd: EventName[TransitionEvent] = event[TransitionEvent]("transition-end")
}
