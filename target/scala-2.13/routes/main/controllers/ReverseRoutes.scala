// @GENERATOR:play-routes-compiler
// @SOURCE:F:/github_repos/SOEN6441_YouTubeAnalyzerProject/conf/routes
// @DATE:Fri Dec 11 06:07:37 EET 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:10
    def getChannellInfo(ID:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "channelInfo/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("ID", ID)))
    }
  
    // @LINE:7
    def getResultWithTerm(term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "get/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
    // @LINE:8
    def socket(term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ws/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
  }

  // @LINE:9
  class ReverseSimilarityController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def similarity(term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "similarity/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
  }

  // @LINE:13
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
