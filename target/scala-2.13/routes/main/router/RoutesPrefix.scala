// @GENERATOR:play-routes-compiler
// @SOURCE:I:/SOEN6441_YouTubeAnalyzerProject_v2.4/conf/routes
// @DATE:Fri Dec 11 03:03:58 EET 2020


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
