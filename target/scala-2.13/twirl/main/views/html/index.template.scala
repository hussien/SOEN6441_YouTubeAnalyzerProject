
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


Seq[Any](format.raw/*2.104*/("""

"""),_display_(/*4.2*/main("Youtube Search API")/*4.28*/ {_display_(Seq[Any](format.raw/*4.30*/("""

 """),format.raw/*6.2*/("""<script src=""""),_display_(/*6.16*/routes/*6.22*/.Assets.versioned("javascripts/youtube.js")),format.raw/*6.65*/("""" type="text/javascript"></script> 
    <div class="s003">
    """),_display_(/*8.6*/helper/*8.12*/.form(routes.HomeController.index())/*8.48*/ {_display_(Seq[Any](format.raw/*8.50*/("""
        """),format.raw/*9.9*/("""<div class="inner-form">
            <div class="input-field second-wrap">
                <input id="search" type="text" name="term" placeholder="Enter Search Keywords..." />
            </div>
            <div class="input-field third-wrap">
                <button class="btn-search" value="cool" type="submit" >
                    GO
                </button>
            </div>
        </div>
        
         <div id = "output"></div> 
        <div data-ws-url=""""),_display_(/*21.28*/routes/*21.34*/.HomeController.socket("sdds")),format.raw/*21.64*/("""">
        <div data-ws-data>
			</div>
        </div>
 <div class="s004">
        """),_display_(/*26.10*/for(sr <- searchResults) yield /*26.34*/ {_display_(Seq[Any](format.raw/*26.36*/("""
            """),format.raw/*27.13*/("""<br/>
            <h1 class="center">Search Query is """"),_display_(/*28.50*/sr/*28.52*/.getTerm),format.raw/*28.60*/(""""</h1>

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
                """),_display_(/*46.18*/for(i <- sr.getItems) yield /*46.39*/ {_display_(Seq[Any](format.raw/*46.41*/("""
                    """),format.raw/*47.21*/("""<tr class="row">
                    	<td>"""),_display_(/*48.27*/i/*48.28*/.getVideoId),format.raw/*48.39*/("""</td>
                        <td>"""),_display_(/*49.30*/i/*49.31*/.getTitle),format.raw/*49.40*/("""</td>
                        <td>"""),_display_(/*50.30*/i/*50.31*/.getChannelTitle),format.raw/*50.47*/("""</td>
                        <td>"""),_display_(/*51.30*/i/*51.31*/.getViewsCount),format.raw/*51.45*/("""</td>
                        <td>"""),_display_(/*52.30*/i/*52.31*/.getDuration),format.raw/*52.43*/("""</td>
                        <td>"""),_display_(/*53.30*/i/*53.31*/.getSimilarity),format.raw/*53.45*/("""</td>
                        <td><!-- """),_display_(/*54.35*/i/*54.36*/.getSentiment),format.raw/*54.49*/("""-->                        
                         """),_display_(/*55.27*/{
								  if (i.getSentiment== 0) {
								    <img src='assets/images/yellow_like.png'/>
								  } else if (i.getSentiment == 1) {
								    <img src='assets/images/green_like.png'/>
								  } else {
								    <img src='assets/images/red_like.png'/>
								  }
							}),format.raw/*63.9*/("""
                        """),format.raw/*64.25*/("""</td>
                    </tr>
                """)))}),format.raw/*66.18*/("""
            """),format.raw/*67.13*/("""</table>
        """)))}),format.raw/*68.10*/("""
    """)))}),format.raw/*69.6*/("""
    """),format.raw/*70.5*/("""</div>
    </div>
""")))}),format.raw/*72.2*/("""
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
                  DATE: 2020-12-05T21:26:28.472
                  SOURCE: I:/SOEN6441_YouTubeAnalyzerProject_v2.3/app/views/index.scala.html
                  HASH: 9030ae718d02e7fa7c0715b4191772613d506af5
                  MATRIX: 610->1|985->32|1183->134|1213->139|1247->165|1286->167|1317->172|1357->186|1371->192|1434->235|1525->301|1539->307|1583->343|1622->345|1658->355|2168->838|2183->844|2234->874|2350->963|2390->987|2430->989|2472->1003|2555->1059|2566->1061|2595->1069|3419->1866|3456->1887|3496->1889|3546->1911|3617->1955|3627->1956|3659->1967|3722->2003|3732->2004|3762->2013|3825->2049|3835->2050|3872->2066|3935->2102|3945->2103|3980->2117|4043->2153|4053->2154|4086->2166|4149->2202|4159->2203|4194->2217|4262->2258|4272->2259|4306->2272|4388->2327|4698->2617|4752->2643|4834->2694|4876->2708|4926->2727|4963->2734|4996->2740|5047->2761
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|37->6|37->6|37->6|37->6|39->8|39->8|39->8|39->8|40->9|52->21|52->21|52->21|57->26|57->26|57->26|58->27|59->28|59->28|59->28|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|83->52|83->52|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|94->63|95->64|97->66|98->67|99->68|100->69|101->70|103->72
                  -- GENERATED --
              */
          