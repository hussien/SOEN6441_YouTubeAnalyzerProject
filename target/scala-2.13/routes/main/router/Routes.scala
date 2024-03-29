// @GENERATOR:play-routes-compiler
// @SOURCE:F:/github_repos/SOEN6441_YouTubeAnalyzerProject/conf/routes
// @DATE:Wed Dec 16 22:03:35 EET 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_2: controllers.HomeController,
  // @LINE:10
  SimilarityController_0: controllers.SimilarityController,
  // @LINE:14
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_2: controllers.HomeController,
    // @LINE:10
    SimilarityController_0: controllers.SimilarityController,
    // @LINE:14
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_2, SimilarityController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, SimilarityController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """get/""" + "$" + """term<[^/]+>""", """controllers.HomeController.getResultWithTerm(term:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """update""", """controllers.HomeController.updateUI(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ws""", """controllers.HomeController.socket()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """similarity/""" + "$" + """term<[^/]+>""", """controllers.SimilarityController.similarity(term:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """channelInfo/""" + "$" + """ID<[^/]+>""", """controllers.HomeController.getChannellInfo(ID:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_2.index(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_HomeController_getResultWithTerm1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("get/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getResultWithTerm1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_2.getResultWithTerm(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getResultWithTerm",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """get/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_updateUI2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("update")))
  )
  private[this] lazy val controllers_HomeController_updateUI2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_2.updateUI(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "updateUI",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """update""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_socket3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ws")))
  )
  private[this] lazy val controllers_HomeController_socket3_invoker = createInvoker(
    HomeController_2.socket(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "socket",
      Nil,
      "GET",
      this.prefix + """ws""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_SimilarityController_similarity4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("similarity/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SimilarityController_similarity4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      SimilarityController_0.similarity(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SimilarityController",
      "similarity",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """similarity/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_getChannellInfo5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("channelInfo/"), DynamicPart("ID", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getChannellInfo5_invoker = createInvoker(
    HomeController_2.getChannellInfo(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getChannellInfo",
      Seq(classOf[String]),
      "GET",
      this.prefix + """channelInfo/""" + "$" + """ID<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(
          req => HomeController_2.index(req))
      }
  
    // @LINE:7
    case controllers_HomeController_getResultWithTerm1_route(params@_) =>
      call(params.fromPath[String]("term", None)) { (term) =>
        controllers_HomeController_getResultWithTerm1_invoker.call(
          req => HomeController_2.getResultWithTerm(term, req))
      }
  
    // @LINE:8
    case controllers_HomeController_updateUI2_route(params@_) =>
      call { 
        controllers_HomeController_updateUI2_invoker.call(
          req => HomeController_2.updateUI(req))
      }
  
    // @LINE:9
    case controllers_HomeController_socket3_route(params@_) =>
      call { 
        controllers_HomeController_socket3_invoker.call(HomeController_2.socket())
      }
  
    // @LINE:10
    case controllers_SimilarityController_similarity4_route(params@_) =>
      call(params.fromPath[String]("term", None)) { (term) =>
        controllers_SimilarityController_similarity4_invoker.call(
          req => SimilarityController_0.similarity(term, req))
      }
  
    // @LINE:11
    case controllers_HomeController_getChannellInfo5_route(params@_) =>
      call(params.fromPath[String]("ID", None)) { (ID) =>
        controllers_HomeController_getChannellInfo5_invoker.call(HomeController_2.getChannellInfo(ID))
      }
  
    // @LINE:14
    case controllers_Assets_versioned6_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
