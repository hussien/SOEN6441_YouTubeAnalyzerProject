// @GENERATOR:play-routes-compiler
// @SOURCE:F:/github_repos/SOEN6441_YouTubeAnalyzerProject/conf/routes
// @DATE:Wed Dec 16 22:03:35 EET 2020

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

  
    // @LINE:7
    def getResultWithTerm(term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "get/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
    // @LINE:11
    def getChannellInfo(ID:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "channelInfo/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("ID", ID)))
    }
  
    // @LINE:9
    def socket(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ws")
    }
  
    // @LINE:8
    def updateUI(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "update")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:10
  class ReverseSimilarityController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def similarity(term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "similarity/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
  }

  // @LINE:14
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
