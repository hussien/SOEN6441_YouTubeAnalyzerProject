// @GENERATOR:play-routes-compiler
// @SOURCE:F:/github_repos/SOEN6441_YouTubeAnalyzerProject/conf/routes
// @DATE:Fri Dec 11 06:07:37 EET 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:10
    def getChannellInfo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getChannellInfo",
      """
        function(ID0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "channelInfo/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("ID", ID0))})
        }
      """
    )
  
    // @LINE:7
    def getResultWithTerm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getResultWithTerm",
      """
        function(term0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "get/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("term", term0))})
        }
      """
    )
  
    // @LINE:8
    def socket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.socket",
      """
        function(term0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ws/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("term", term0))})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseSimilarityController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def similarity: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SimilarityController.similarity",
      """
        function(term0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "similarity/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("term", term0))})
        }
      """
    )
  
  }

  // @LINE:13
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
