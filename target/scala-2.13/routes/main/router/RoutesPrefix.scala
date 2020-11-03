// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Administrator/Downloads/youtube-analyzer/conf/routes
// @DATE:Fri Oct 30 23:01:20 EET 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
