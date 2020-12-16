
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
        
        
 <div class="s004">
        """),_display_(/*22.10*/for(sr <- searchResults) yield /*22.34*/ {_display_(Seq[Any](format.raw/*22.36*/("""
            """),format.raw/*23.13*/("""<br/>
            <h1 class="center">Search Query is """"),_display_(/*24.50*/sr/*24.52*/.getTerm),format.raw/*24.60*/(""""</h1>

			Click here to get the sentiments :- <a href="/similarity/"""),_display_(/*26.62*/sr/*26.64*/.getTerm),format.raw/*26.72*/("""">"""),_display_(/*26.75*/sr/*26.77*/.getTerm),format.raw/*26.85*/("""</a>
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
                """),_display_(/*46.18*/for(i <- sr.getItems) yield /*46.39*/ {_display_(Seq[Any](format.raw/*46.41*/("""
                    """),format.raw/*47.21*/("""<tr class="row">
                    	<td>"""),_display_(/*48.27*/i/*48.28*/.getVideoId),format.raw/*48.39*/("""</td>
                        <td>"""),_display_(/*49.30*/i/*49.31*/.getTitle),format.raw/*49.40*/("""</td>
                        <td>
                         <a href="channelInfo/"""),_display_(/*51.48*/i/*51.49*/.getChannelId),format.raw/*51.62*/("""">"""),_display_(/*51.65*/i/*51.66*/.getChannelTitle),format.raw/*51.82*/("""</a>
                        </td>
                        <td>"""),_display_(/*53.30*/i/*53.31*/.getViewsCount),format.raw/*53.45*/("""</td>
                        <td>"""),_display_(/*54.30*/i/*54.31*/.getDuration),format.raw/*54.43*/("""</td>
                        <td>"""),_display_(/*55.30*/i/*55.31*/.getSimilarity),format.raw/*55.45*/("""</td>
                        <td><!-- """),_display_(/*56.35*/i/*56.36*/.getSentiment),format.raw/*56.49*/("""-->                        
                         """),_display_(/*57.27*/{
								  if (i.getSentiment== 0) {
								    <img src='assets/images/yellow_like.png'/>
								  } else if (i.getSentiment == 1) {
								    <img src='assets/images/green_like.png'/>
								  } else {
								    <img src='assets/images/red_like.png'/>
								  }
							}),format.raw/*65.9*/("""
                        """),format.raw/*66.25*/("""</td>
                    </tr>
                """)))}),format.raw/*68.18*/("""
            """),format.raw/*69.13*/("""</table>
        """)))}),format.raw/*70.10*/("""
    """)))}),format.raw/*71.6*/("""
    """),format.raw/*72.5*/("""</div>
    </div>
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
                  DATE: 2020-12-16T22:05:27.757
                  SOURCE: F:/github_repos/SOEN6441_YouTubeAnalyzerProject/app/views/index.scala.html
                  HASH: 68c495d6ec408d174ded1015df3fba255bc31679
                  MATRIX: 610->1|985->32|1183->134|1213->139|1247->165|1286->167|1317->172|1357->186|1371->192|1434->235|1529->305|1543->311|1587->347|1626->349|1662->359|2148->818|2188->842|2228->844|2270->858|2353->914|2364->916|2393->924|2491->995|2502->997|2531->1005|2561->1008|2572->1010|2601->1018|3448->1838|3485->1859|3525->1861|3575->1883|3646->1927|3656->1928|3688->1939|3751->1975|3761->1976|3791->1985|3902->2069|3912->2070|3946->2083|3976->2086|3986->2087|4023->2103|4116->2169|4126->2170|4161->2184|4224->2220|4234->2221|4267->2233|4330->2269|4340->2270|4375->2284|4443->2325|4453->2326|4487->2339|4569->2394|4879->2684|4933->2710|5015->2761|5057->2775|5107->2794|5144->2801|5177->2807|5228->2828
                  LINES: 23->1|28->2|33->2|35->4|35->4|35->4|37->6|37->6|37->6|37->6|39->8|39->8|39->8|39->8|40->9|53->22|53->22|53->22|54->23|55->24|55->24|55->24|57->26|57->26|57->26|57->26|57->26|57->26|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|82->51|82->51|82->51|82->51|82->51|82->51|84->53|84->53|84->53|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|88->57|96->65|97->66|99->68|100->69|101->70|102->71|103->72|105->74
                  -- GENERATED --
              */
          