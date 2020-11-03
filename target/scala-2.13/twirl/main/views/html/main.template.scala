
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""
"""),format.raw/*9.1*/("""<html lang="en">
<head>
    """),format.raw/*11.58*/("""
    """),format.raw/*12.5*/("""<title>"""),_display_(/*12.13*/title),format.raw/*12.18*/("""</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
    <link rel="stylesheet" media="screen" href=""""),_display_(/*14.50*/routes/*14.56*/.Assets.versioned("stylesheets/style.css")),format.raw/*14.98*/("""">
    <style>
    table, th, td """),format.raw/*16.19*/("""{"""),format.raw/*16.20*/("""
		  """),format.raw/*17.5*/("""border:3px dashed #6e0979;
		"""),format.raw/*18.3*/("""}"""),format.raw/*18.4*/("""
    """),format.raw/*19.5*/("""</style>
</head>


    <body>
        """),format.raw/*25.32*/("""
        """),_display_(/*26.10*/content),format.raw/*26.17*/("""

        """),format.raw/*28.9*/("""<script src=""""),_display_(/*28.23*/routes/*28.29*/.Assets.versioned("javascripts/script.js")),format.raw/*28.71*/("""" type="text/javascript"></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-10-31T18:21:33.152
                  SOURCE: C:/Users/Administrator/Downloads/youtube-analyzer/app/views/main.scala.html
                  HASH: 542a1c842f48b308f62b4c826f9c1d2a3acc675d
                  MATRIX: 1165->260|1289->291|1316->292|1372->373|1404->378|1439->386|1465->391|1635->534|1650->540|1713->582|1774->615|1803->616|1835->621|1891->650|1919->651|1951->656|2017->784|2054->794|2082->801|2119->811|2160->825|2175->831|2238->873
                  LINES: 32->7|37->8|38->9|40->11|41->12|41->12|41->12|43->14|43->14|43->14|45->16|45->16|46->17|47->18|47->18|48->19|53->25|54->26|54->26|56->28|56->28|56->28|56->28
                  -- GENERATED --
              */
          