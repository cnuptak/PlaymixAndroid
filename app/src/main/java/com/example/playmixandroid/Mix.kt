class Mix (val dj: String, val title: String, var number: Short, val timecode: Int, val duration: Int, val format: String, bitrate: Int){

    init {
        print("$timecode $dj - $title")
    }

    fun play () {

    }
}