package nfjs.geocoder

class Geocoder {
    public static final String BASE =
            'https://maps.googleapis.com/maps/api/geocode/xml?'

    Stadium fillInLatLng(Stadium stadium) {
        String encoded = [stadium.street, stadium.city,
                          stadium.state].collect {
            URLEncoder.encode(it, 'UTF-8')
        }.join(',')
        String qs = "address=$encoded"
        def root = new XmlSlurper().parse("$BASE$qs")
        def loc = root.result[0].geometry.location
        stadium.latitude = loc.lat.toBigDecimal()
        stadium.longitude = loc.lng.toBigDecimal()
        stadium
    }
}
