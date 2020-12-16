// @GENERATOR:play-routes-compiler
// @SOURCE:F:/github_repos/SOEN6441_YouTubeAnalyzerProject/conf/routes
// @DATE:Wed Dec 16 22:03:35 EET 2020


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
