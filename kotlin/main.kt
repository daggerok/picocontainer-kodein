package daggerok

import com.github.salomonbrys.kodein.*
import spark.Spark.*

interface KotlinService {
  fun getName(): String
}

class KotlinServiceImpl : KotlinService {
  override fun getName() = "Max"
}

val ioc = Kodein {
  bind<KotlinService>() with singleton { KotlinServiceImpl() }
}

fun main(args: Array<String>) {

  val service = ioc.instance<KotlinService>()

  port(8080)
  path("/", {
    get("*", { _, _ ->
      "hi, ${service.getName()} from kotlin"
    })
  })
}

