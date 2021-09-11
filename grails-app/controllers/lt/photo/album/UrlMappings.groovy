package lt.photo.album

class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?(.$format)?" {
      constraints {
        // apply constraints here
      }
    }
    "/photos"(controller: "photoController", action: "printPhotosByAlbum")

    "/"(view: "/index")
    "500"(view: '/error')
    "404"(view: '/notFound')
  }
}
