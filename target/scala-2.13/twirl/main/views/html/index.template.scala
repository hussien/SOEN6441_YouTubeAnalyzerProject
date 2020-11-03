
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
/*1.2*/import play.mvc.Http.Request

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[List[SearchResult],Form[Search],Request,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(searchResults: List[SearchResult], form: Form[Search])(implicit request: Request, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("Youtube Search API")/*4.28*/ {_display_(Seq[Any](format.raw/*4.30*/("""

    """),format.raw/*6.5*/("""<div class="s003">
    """),_display_(/*7.6*/helper/*7.12*/.form(routes.HomeController.index())/*7.48*/ {_display_(Seq[Any](format.raw/*7.50*/("""
        """),format.raw/*8.9*/("""<div class="inner-form">
            <div class="input-field second-wrap">
                <input id="search" type="text" name="term" placeholder="Enter Search Keywords..." />
            </div>
            <div class="input-field third-wrap">
                <button class="btn-search" type="submit" >
                    GO
                </button>
            </div>
        </div>

        """),_display_(/*19.10*/for(sr <- searchResults) yield /*19.34*/ {_display_(Seq[Any](format.raw/*19.36*/("""
            """),format.raw/*20.13*/("""<br/>
            <h1 class="center">Search Query is """"),_display_(/*21.50*/sr/*21.52*/.getTerm),format.raw/*21.60*/(""""</h1>

            <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%" style="text-align:center;">
                <thead>
                    <tr>
                    	<th class="th-sm">VID</th>
                        <th class="th-sm">Title</th>
                        <th class="th-sm">Channel Title</th>
                        <th class="th-sm">Duration</th>
                        <th class="th-sm">Similarity</th>
                        <th class="th-sm">Comments</th>
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*35.18*/for(i <- sr.getItems) yield /*35.39*/ {_display_(Seq[Any](format.raw/*35.41*/("""
                    """),format.raw/*36.21*/("""<tr>
                    	<td>"""),_display_(/*37.27*/i/*37.28*/.getVideoId),format.raw/*37.39*/("""</td>
                        <td>"""),_display_(/*38.30*/i/*38.31*/.getTitle),format.raw/*38.40*/("""</td>
                        <td>"""),_display_(/*39.30*/i/*39.31*/.getChannelTitle),format.raw/*39.47*/("""</td>
                        <td>"""),_display_(/*40.30*/i/*40.31*/.getDuration),format.raw/*40.43*/("""</td>
                        <td>"""),_display_(/*41.30*/i/*41.31*/.getSimilarity),format.raw/*41.45*/("""</td>
                        <td>
                        	"""),_display_(/*43.27*/for(c <- i.getComments ) yield /*43.51*/ {_display_(Seq[Any](format.raw/*43.53*/("""
                        		"""),format.raw/*44.27*/("""<p>"""),_display_(/*44.31*/c),format.raw/*44.32*/("""</p>
                        	""")))}),format.raw/*45.27*/("""
                        		
                        """),format.raw/*47.25*/("""</td>
                    </tr>
                """)))}),format.raw/*49.18*/("""
            """),format.raw/*50.13*/("""</table>
        """)))}),format.raw/*51.10*/("""
    """)))}),format.raw/*52.6*/("""
    """),format.raw/*53.5*/("""</div>
""")))}),format.raw/*54.2*/("""
"""))
      }
    }
  }

  def render(searchResults:List[SearchResult],form:Form[Search],request:Request,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(searchResults,form)(request,messages)

  def f:((List[SearchResult],Form[Search]) => (Request,Messages) => play.twirl.api.HtmlFormat.Appendable) = (searchResults,form) => (request,messages) => apply(searchResults,form)(request,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-11-01T23:08:26.537
                  SOURCE: C:/Users/Administrator/Downloads/youtube-analyzer/app/views/index.scala.html
                  HASH: 0f48b7ff1d77656ef7ebb4b42cbbbf762c3674dc
                  MATRIX: 610->1|985->31|1181->134|1208->136|1242->162|1281->164|1313->170|1362->194|1376->200|1420->236|1459->238|1494->247|1917->643|1957->667|1997->669|2038->682|2120->737|2131->739|2160->747|2816->1376|2853->1397|2893->1399|2942->1420|3000->1451|3010->1452|3042->1463|3104->1498|3114->1499|3144->1508|3206->1543|3216->1544|3253->1560|3315->1595|3325->1596|3358->1608|3420->1643|3430->1644|3465->1658|3553->1719|3593->1743|3633->1745|3688->1772|3719->1776|3741->1777|3803->1808|3883->1860|3963->1909|4004->1922|4053->1940|4089->1946|4121->1951|4159->1959
                  LINES: 23->1|28->2|33->3|34->4|34->4|34->4|36->6|37->7|37->7|37->7|37->7|38->8|49->19|49->19|49->19|50->20|51->21|51->21|51->21|65->35|65->35|65->35|66->36|67->37|67->37|67->37|68->38|68->38|68->38|69->39|69->39|69->39|70->40|70->40|70->40|71->41|71->41|71->41|73->43|73->43|73->43|74->44|74->44|74->44|75->45|77->47|79->49|80->50|81->51|82->52|83->53|84->54
                  -- GENERATED --
              */
          