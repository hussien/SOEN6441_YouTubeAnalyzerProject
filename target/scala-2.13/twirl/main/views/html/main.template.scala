
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


Seq[Any](format.raw/*7.32*/("""

"""),format.raw/*9.1*/("""<html lang="en">
<head>
 <script type="text/javascript" src=""""),_display_(/*11.39*/routes/*11.45*/.Assets.versioned("javascripts/jquery.min.js")),format.raw/*11.91*/(""""></script>
   
    """),format.raw/*13.58*/("""
    """),format.raw/*14.5*/("""<title>"""),_display_(/*14.13*/title),format.raw/*14.18*/("""</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
    <link rel="stylesheet" media="screen" href=""""),_display_(/*16.50*/routes/*16.56*/.Assets.versioned("stylesheets/style.css")),format.raw/*16.98*/("""">
    <style>
    table, th, td """),format.raw/*18.19*/("""{"""),format.raw/*18.20*/("""
		  """),format.raw/*19.5*/("""border:3px dashed #6e0979;
		"""),format.raw/*20.3*/("""}"""),format.raw/*20.4*/("""
    """),format.raw/*21.5*/("""</style>
   
</head>


    <body>
        """),format.raw/*28.32*/("""
        """),_display_(/*29.10*/content),format.raw/*29.17*/("""

        """),format.raw/*31.9*/("""<script src=""""),_display_(/*31.23*/routes/*31.29*/.Assets.versioned("javascripts/script.js")),format.raw/*31.71*/("""" type="text/javascript"></script>
     
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
                  DATE: 2020-12-05T21:26:28.522
                  SOURCE: I:/SOEN6441_YouTubeAnalyzerProject_v2.3/app/views/main.scala.html
                  HASH: b8e1520c000093a9f44a0feb24e2ee117a2913a0
                  MATRIX: 1170->266|1295->296|1325->300|1416->364|1431->370|1498->416|1548->491|1581->497|1616->505|1642->510|1814->655|1829->661|1892->703|1955->738|1984->739|2017->745|2074->775|2102->776|2135->782|2211->921|2249->932|2277->939|2316->951|2357->965|2372->971|2435->1013
                  LINES: 32->7|37->7|39->9|41->11|41->11|41->11|43->13|44->14|44->14|44->14|46->16|46->16|46->16|48->18|48->18|49->19|50->20|50->20|51->21|57->28|58->29|58->29|60->31|60->31|60->31|60->31
                  -- GENERATED --
              */
          