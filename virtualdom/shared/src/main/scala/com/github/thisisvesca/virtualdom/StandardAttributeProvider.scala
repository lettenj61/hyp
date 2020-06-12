package com.github.thisisvesca
package virtualdom

import VirtualDom._

// GENERATED CODE ... DO NOT EDIT MANUALLY

trait StandardAttributeProvider extends AttributeProvider {
  class CellPaddingName extends AttributeName[Double]("cellPadding") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class CellSpacingName extends AttributeName[Double]("cellSpacing") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class DownloadName extends AttributeName[String]("download") {
    def create[F](value: Boolean): Attribute[F] = new Property(this.name, value)
    def := [F](value: Boolean): Attribute[F] = create(value)
  }

  class FrameBorderName extends AttributeName[Double]("frameBorder") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class HeightName extends AttributeName[Double]("height") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class MaxName extends AttributeName[Double]("max") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class MinName extends AttributeName[Double]("min") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class StepName extends AttributeName[Double]("step") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  class ValueName extends AttributeName[String]("value") {
    def create[F](value: Double): Attribute[F] = new Property(this.name, value)
    def := [F](value: Double): Attribute[F] = create(value)
  }

  class VolumeName extends AttributeName[String]("volume") {
    def create[F](value: Double): Attribute[F] = new Property(this.name, value)
    def := [F](value: Double): Attribute[F] = create(value)
  }

  class WidthName extends AttributeName[Double]("width") {
    def create[F](value: String): Attribute[F] = new Property(this.name, value)
    def := [F](value: String): Attribute[F] = create(value)
  }

  lazy val accept : AttributeName[String] = attr[String]("accept")
  lazy val acceptCharset : AttributeName[String] = attr[String]("acceptCharset")
  lazy val accessKey : AttributeName[String] = attr[String]("accessKey")
  lazy val action : AttributeName[String] = attr[String]("action")
  lazy val allowFullScreen : AttributeName[Boolean] = attr[Boolean]("allowFullScreen")
  lazy val allowTransparency : AttributeName[Boolean] = attr[Boolean]("allowTransparency")
  lazy val alt : AttributeName[String] = attr[String]("alt")
  lazy val as : AttributeName[String] = attr[String]("as")
  lazy val async : AttributeName[Boolean] = attr[Boolean]("async")
  lazy val autoComplete : AttributeName[String] = attr[String]("autoComplete")
  lazy val autoCorrect : AttributeName[String] = attr[String]("autoCorrect")
  lazy val autoFocus : AttributeName[Boolean] = attr[Boolean]("autoFocus")
  lazy val autoPlay : AttributeName[Boolean] = attr[Boolean]("autoPlay")
  lazy val capture : AttributeName[Boolean] = attr[Boolean]("capture")
  lazy val cellPadding: CellPaddingName = new CellPaddingName

  lazy val cellSpacing: CellSpacingName = new CellSpacingName

  lazy val charSet : AttributeName[String] = attr[String]("charSet")
  lazy val challenge : AttributeName[String] = attr[String]("challenge")
  lazy val checked : AttributeName[Boolean] = attr[Boolean]("checked")
  lazy val className: AttributeName[String] = attr[String]("class")
  lazy val cols : AttributeName[Double] = attr[Double]("cols")
  lazy val colSpan : AttributeName[Double] = attr[Double]("colSpan")
  lazy val content : AttributeName[String] = attr[String]("content")
  lazy val contentEditable : AttributeName[Boolean] = attr[Boolean]("contentEditable")
  lazy val contextMenu : AttributeName[String] = attr[String]("contextMenu")
  lazy val controls : AttributeName[Boolean] = attr[Boolean]("controls")
  lazy val controlsList : AttributeName[String] = attr[String]("controlsList")
  lazy val coords : AttributeName[String] = attr[String]("coords")
  lazy val crossOrigin : AttributeName[String] = attr[String]("crossOrigin")
  lazy val data : AttributeName[String] = attr[String]("data")
  lazy val dateTime : AttributeName[String] = attr[String]("dateTime")
  lazy val default : AttributeName[Boolean] = attr[Boolean]("default")
  lazy val defer : AttributeName[Boolean] = attr[Boolean]("defer")
  lazy val dir : AttributeName[String] = attr[String]("dir")
  lazy val disabled : AttributeName[Boolean] = attr[Boolean]("disabled")
  lazy val disableRemotePlayback : AttributeName[Boolean] = attr[Boolean]("disableRemotePlayback")
  lazy val download: DownloadName = new DownloadName

  lazy val draggable : AttributeName[Boolean] = attr[Boolean]("draggable")
  lazy val encType : AttributeName[String] = attr[String]("encType")
  lazy val form : AttributeName[String] = attr[String]("form")
  lazy val formAction : AttributeName[String] = attr[String]("formAction")
  lazy val formEncType : AttributeName[String] = attr[String]("formEncType")
  lazy val formMethod : AttributeName[String] = attr[String]("formMethod")
  lazy val formNoValidate : AttributeName[Boolean] = attr[Boolean]("formNoValidate")
  lazy val formTarget : AttributeName[String] = attr[String]("formTarget")
  lazy val frameBorder: FrameBorderName = new FrameBorderName

  lazy val headers : AttributeName[String] = attr[String]("headers")
  lazy val height: HeightName = new HeightName

  lazy val hidden : AttributeName[Boolean] = attr[Boolean]("hidden")
  lazy val high : AttributeName[Double] = attr[Double]("high")
  lazy val href : AttributeName[String] = attr[String]("href")
  lazy val hrefLang : AttributeName[String] = attr[String]("hrefLang")
  lazy val for_ : AttributeName[String] = attr[String]("for_")
  lazy val htmlFor : AttributeName[String] = attr[String]("htmlFor")
  lazy val httpEquiv : AttributeName[String] = attr[String]("httpEquiv")
  lazy val icon : AttributeName[String] = attr[String]("icon")
  lazy val id : AttributeName[String] = attr[String]("id")
  lazy val inputMode : AttributeName[String] = attr[String]("inputMode")
  lazy val integrity : AttributeName[String] = attr[String]("integrity")
  lazy val is : AttributeName[String] = attr[String]("is")
  lazy val keyParams : AttributeName[String] = attr[String]("keyParams")
  lazy val keyType : AttributeName[String] = attr[String]("keyType")
  lazy val kind : AttributeName[String] = attr[String]("kind")
  lazy val label : AttributeName[String] = attr[String]("label")
  lazy val lang : AttributeName[String] = attr[String]("lang")
  lazy val list : AttributeName[String] = attr[String]("list")
  lazy val loading : AttributeName[String] = attr[String]("loading")
  lazy val loop : AttributeName[Boolean] = attr[Boolean]("loop")
  lazy val low : AttributeName[Double] = attr[Double]("low")
  lazy val manifest : AttributeName[String] = attr[String]("manifest")
  lazy val marginHeight : AttributeName[Double] = attr[Double]("marginHeight")
  lazy val marginWidth : AttributeName[Double] = attr[Double]("marginWidth")
  lazy val max: MaxName = new MaxName

  lazy val maxLength : AttributeName[Double] = attr[Double]("maxLength")
  lazy val media : AttributeName[String] = attr[String]("media")
  lazy val mediaGroup : AttributeName[String] = attr[String]("mediaGroup")
  lazy val method : AttributeName[String] = attr[String]("method")
  lazy val min: MinName = new MinName

  lazy val minLength : AttributeName[Double] = attr[Double]("minLength")
  lazy val multiple : AttributeName[Boolean] = attr[Boolean]("multiple")
  lazy val muted : AttributeName[Boolean] = attr[Boolean]("muted")
  lazy val name : AttributeName[String] = attr[String]("name")
  lazy val nonce : AttributeName[String] = attr[String]("nonce")
  lazy val noValidate : AttributeName[Boolean] = attr[Boolean]("noValidate")
  lazy val open : AttributeName[Boolean] = attr[Boolean]("open")
  lazy val optimum : AttributeName[Double] = attr[Double]("optimum")
  lazy val pattern : AttributeName[String] = attr[String]("pattern")
  lazy val placeholder : AttributeName[String] = attr[String]("placeholder")
  lazy val playsInline : AttributeName[Boolean] = attr[Boolean]("playsInline")
  lazy val poster : AttributeName[String] = attr[String]("poster")
  lazy val preload : AttributeName[String] = attr[String]("preload")
  lazy val radioGroup : AttributeName[String] = attr[String]("radioGroup")
  lazy val readOnly : AttributeName[Boolean] = attr[Boolean]("readOnly")
  lazy val rel : AttributeName[String] = attr[String]("rel")
  lazy val required : AttributeName[Boolean] = attr[Boolean]("required")
  lazy val role : AttributeName[String] = attr[String]("role")
  lazy val rows : AttributeName[Double] = attr[Double]("rows")
  lazy val rowSpan : AttributeName[Double] = attr[Double]("rowSpan")
  lazy val sandbox : AttributeName[String] = attr[String]("sandbox")
  lazy val scope : AttributeName[String] = attr[String]("scope")
  lazy val scoped : AttributeName[Boolean] = attr[Boolean]("scoped")
  lazy val scrolling : AttributeName[String] = attr[String]("scrolling")
  lazy val seamless : AttributeName[Boolean] = attr[Boolean]("seamless")
  lazy val selected : AttributeName[Boolean] = attr[Boolean]("selected")
  lazy val shape : AttributeName[String] = attr[String]("shape")
  lazy val size : AttributeName[Double] = attr[Double]("size")
  lazy val sizes : AttributeName[String] = attr[String]("sizes")
  lazy val slot : AttributeName[String] = attr[String]("slot")
  lazy val span_ : AttributeName[Double] = attr[Double]("span")
  lazy val spellcheck : AttributeName[Boolean] = attr[Boolean]("spellcheck")
  lazy val src : AttributeName[String] = attr[String]("src")
  lazy val srcset : AttributeName[String] = attr[String]("srcset")
  lazy val srcDoc : AttributeName[String] = attr[String]("srcDoc")
  lazy val srcLang : AttributeName[String] = attr[String]("srcLang")
  lazy val srcSet : AttributeName[String] = attr[String]("srcSet")
  lazy val start : AttributeName[Double] = attr[Double]("start")
  lazy val step: StepName = new StepName

  lazy val summary : AttributeName[String] = attr[String]("summary")
  lazy val tabIndex : AttributeName[Double] = attr[Double]("tabIndex")
  lazy val target : AttributeName[String] = attr[String]("target")
  lazy val title : AttributeName[String] = attr[String]("title")
  lazy val type_ : AttributeName[String] = attr[String]("type_")
  lazy val useMap : AttributeName[String] = attr[String]("useMap")
  lazy val value: ValueName = new ValueName

  lazy val volume: VolumeName = new VolumeName

  lazy val width: WidthName = new WidthName

  lazy val wmode : AttributeName[String] = attr[String]("wmode")
  lazy val wrap : AttributeName[String] = attr[String]("wrap")
  lazy val about : AttributeName[String] = attr[String]("about")
  lazy val datatype : AttributeName[String] = attr[String]("datatype")
  lazy val inlist : AttributeName[String] = attr[String]("inlist")
  lazy val prefix : AttributeName[String] = attr[String]("prefix")
  lazy val property : AttributeName[String] = attr[String]("property")
  lazy val resource : AttributeName[String] = attr[String]("resource")
  lazy val typeof : AttributeName[String] = attr[String]("typeof")
  lazy val vocab : AttributeName[String] = attr[String]("vocab")
  lazy val itemProp : AttributeName[String] = attr[String]("itemProp")
  lazy val itemScope : AttributeName[Boolean] = attr[Boolean]("itemScope")
  lazy val itemType : AttributeName[String] = attr[String]("itemType")
  lazy val itemID : AttributeName[String] = attr[String]("itemID")
  lazy val itemRef : AttributeName[String] = attr[String]("itemRef")
}
