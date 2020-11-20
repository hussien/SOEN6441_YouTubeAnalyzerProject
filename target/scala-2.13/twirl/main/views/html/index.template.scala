
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

            <table id="dtBasicExample" class="table table-striped" cellspacing="0" width="100%" style="text-align:center;">
                <thead>
                    <tr>
                    	<th class="th-sm">VID</th>
                        <th class="th-sm">Video Title</th>
                        <th class="th-sm">Owner</th>
                        <th class="th-sm">View_Count</th>
                        <th class="th-sm">time_lapsed</th>
                        <th class="th-sm">Similarity</th>
                        <th class="th-sm">Sentiment</th>
                        <!-- 
                        <th class="th-sm">Comments</th>
                        -->
                    </tr>
                </thead>
                <tbody>
                """),_display_(/*39.18*/for(i <- sr.getItems) yield /*39.39*/ {_display_(Seq[Any](format.raw/*39.41*/("""
                    """),format.raw/*40.21*/("""<tr class="row">
                    	<td>"""),_display_(/*41.27*/i/*41.28*/.getVideoId),format.raw/*41.39*/("""</td>
                        <td>"""),_display_(/*42.30*/i/*42.31*/.getTitle),format.raw/*42.40*/("""</td>
                        <td>"""),_display_(/*43.30*/i/*43.31*/.getChannelTitle),format.raw/*43.47*/("""</td>
                        <td>"""),_display_(/*44.30*/i/*44.31*/.getViewsCount),format.raw/*44.45*/("""</td>
                        <td>"""),_display_(/*45.30*/i/*45.31*/.getDuration),format.raw/*45.43*/("""</td>
                        <td>"""),_display_(/*46.30*/i/*46.31*/.getSimilarity),format.raw/*46.45*/("""</td>
                        <td><!-- """),_display_(/*47.35*/i/*47.36*/.getSentiment),format.raw/*47.49*/("""-->                        
                         """),_display_(/*48.27*/{
								  if (i.getSentiment== 0) {
								    <img src='assets/images/yellow_like.png'/>
								  } else if (i.getSentiment == 1) {
								    <img src='assets/images/green_like.png'/>
								  } else {
								    <img src='assets/images/red_like.png'/>
								  }
							}),format.raw/*56.9*/("""
                        """),format.raw/*57.25*/("""</td>
                        <!-- 
                        <td>"""),_display_(/*59.30*/i/*59.31*/.getEmoijIcons),format.raw/*59.45*/("""</td>
                        -->
                        <!-- <td>
                        	"""),_display_(/*62.27*/for(c <- i.getComments ) yield /*62.51*/ {_display_(Seq[Any](format.raw/*62.53*/("""
                        		"""),format.raw/*63.27*/("""<p>"""),_display_(/*63.31*/c),format.raw/*63.32*/("""</p>
                        	""")))}),format.raw/*64.27*/("""
                        		
                        """),format.raw/*66.25*/("""</td>
                        -->
                    </tr>
                """)))}),format.raw/*69.18*/("""
            """),format.raw/*70.13*/("""</table>
        """)))}),format.raw/*71.10*/("""
    """)))}),format.raw/*72.6*/("""
    """),format.raw/*73.5*/("""</div>
""")))}),format.raw/*74.2*/("""
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
                  DATE: 2020-11-20T18:03:57.831
                  SOURCE: F:/github_repos/SOEN6441_YouTubeAnalyzerProject/app/views/index.scala.html
                  HASH: b012c57ca9d665ef41dd4a2f0da17b1ca92620a2
                  MATRIX: 610->1|985->31|1181->134|1208->136|1242->162|1281->164|1313->170|1362->194|1376->200|1420->236|1459->238|1494->247|1917->643|1957->667|1997->669|2038->682|2120->737|2131->739|2160->747|2966->1526|3003->1547|3043->1549|3092->1570|3162->1613|3172->1614|3204->1625|3266->1660|3276->1661|3306->1670|3368->1705|3378->1706|3415->1722|3477->1757|3487->1758|3522->1772|3584->1807|3594->1808|3627->1820|3689->1855|3699->1856|3734->1870|3801->1910|3811->1911|3845->1924|3926->1978|4228->2260|4281->2285|4373->2350|4383->2351|4418->2365|4539->2459|4579->2483|4619->2485|4674->2512|4705->2516|4727->2517|4789->2548|4869->2600|4977->2677|5018->2690|5067->2708|5103->2714|5135->2719|5173->2727
                  LINES: 23->1|28->2|33->3|34->4|34->4|34->4|36->6|37->7|37->7|37->7|37->7|38->8|49->19|49->19|49->19|50->20|51->21|51->21|51->21|69->39|69->39|69->39|70->40|71->41|71->41|71->41|72->42|72->42|72->42|73->43|73->43|73->43|74->44|74->44|74->44|75->45|75->45|75->45|76->46|76->46|76->46|77->47|77->47|77->47|78->48|86->56|87->57|89->59|89->59|89->59|92->62|92->62|92->62|93->63|93->63|93->63|94->64|96->66|99->69|100->70|101->71|102->72|103->73|104->74
                  -- GENERATED --
              */
          