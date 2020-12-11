
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

 """),format.raw/*6.2*/("""<script src=""""),_display_(/*6.16*/routes/*6.22*/.Assets.versioned("javascripts/youtube.js")),format.raw/*6.65*/("""?v=2" type="text/javascript"></script> 
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

			Click here to get the sentiments :- <a href="/similarity/"""),_display_(/*30.62*/sr/*30.64*/.getTerm),format.raw/*30.72*/("""">"""),_display_(/*30.75*/sr/*30.77*/.getTerm),format.raw/*30.85*/("""</a>
			<br>
			<br>
			<br>
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
                """),_display_(/*50.18*/for(i <- sr.getItems) yield /*50.39*/ {_display_(Seq[Any](format.raw/*50.41*/("""
                    """),format.raw/*51.21*/("""<tr class="row">
                    	<td>"""),_display_(/*52.27*/i/*52.28*/.getVideoId),format.raw/*52.39*/("""</td>
                        <td>"""),_display_(/*53.30*/i/*53.31*/.getTitle),format.raw/*53.40*/("""</td>
                        <td>
                         <a href="channelInfo/"""),_display_(/*55.48*/i/*55.49*/.getChannelId),format.raw/*55.62*/("""">"""),_display_(/*55.65*/i/*55.66*/.getChannelTitle),format.raw/*55.82*/("""</a>
                        </td>
                        <td>"""),_display_(/*57.30*/i/*57.31*/.getViewsCount),format.raw/*57.45*/("""</td>
                        <td>"""),_display_(/*58.30*/i/*58.31*/.getDuration),format.raw/*58.43*/("""</td>
                        <td>"""),_display_(/*59.30*/i/*59.31*/.getSimilarity),format.raw/*59.45*/("""</td>
                        <td><!-- """),_display_(/*60.35*/i/*60.36*/.getSentiment),format.raw/*60.49*/("""-->                        
                         """),_display_(/*61.27*/{
								  if (i.getSentiment== 0) {
								    <img src='assets/images/yellow_like.png'/>
								  } else if (i.getSentiment == 1) {
								    <img src='assets/images/green_like.png'/>
								  } else {
								    <img src='assets/images/red_like.png'/>
								  }
							}),format.raw/*69.9*/("""
                        """),format.raw/*70.25*/("""</td>
                    </tr>
                """)))}),format.raw/*72.18*/("""
            """),format.raw/*73.13*/("""</table>
        """)))}),format.raw/*74.10*/("""
    """)))}),format.raw/*75.6*/("""
    """),format.raw/*76.5*/("""</div>
    </div>
""")))}),format.raw/*78.2*/("""
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
                  DATE: 2020-12-11T03:03:58.532
                  SOURCE: I:/SOEN6441_YouTubeAnalyzerProject_v2.4/app/views/index.scala.html
                  HASH: c5bb4c4f08d0315574ef32e8195abc652daf0099
                  MATRIX: 610->1|985->32|1183->134|1213->139|1247->165|1286->167|1317->172|1357->186|1371->192|1434->235|1529->305|1543->311|1587->347|1626->349|1662->359|2172->842|2187->848|2238->878|2354->967|2394->991|2434->993|2476->1007|2559->1063|2570->1065|2599->1073|2697->1144|2708->1146|2737->1154|2767->1157|2778->1159|2807->1167|3654->1987|3691->2008|3731->2010|3781->2032|3852->2076|3862->2077|3894->2088|3957->2124|3967->2125|3997->2134|4108->2218|4118->2219|4152->2232|4182->2235|4192->2236|4229->2252|4322->2318|4332->2319|4367->2333|4430->2369|4440->2370|4473->2382|4536->2418|4546->2419|4581->2433|4649->2474|4659->2475|4693->2488|4775->2543|5085->2833|5139->2859|5221->2910|5263->2924|5313->2943|5350->2950|5383->2956|5434->2977
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|37->6|37->6|37->6|37->6|39->8|39->8|39->8|39->8|40->9|52->21|52->21|52->21|57->26|57->26|57->26|58->27|59->28|59->28|59->28|61->30|61->30|61->30|61->30|61->30|61->30|81->50|81->50|81->50|82->51|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|86->55|86->55|86->55|86->55|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|100->69|101->70|103->72|104->73|105->74|106->75|107->76|109->78
                  -- GENERATED --
              */
          