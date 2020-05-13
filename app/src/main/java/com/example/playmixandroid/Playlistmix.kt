fun main(args: Array<String>) {
    val name = "Playlistmix"
    //Application name
    val version = "1.0"
    // Application version
    println("$name v. $version by fRee")
    // Start output of application
    println("Enter URL of mix (Soundcloud or another resource)")
    val userInput2 = readLine()
    val itog = acceptableMixUrl(userInput2)
    var duration = 125
    // Duration of audiofile
    println(if (duration < 30) "Audiofile is too short" else "Acceptable file length: $duration seconds")
    // Check track length
    var partnumber = 0
    // Number of parts of 30 seconds
    val durationOfPart = 30
    // Duration of 1 part
    while (duration >= durationOfPart) {
        duration = duration - durationOfPart
        partnumber = partnumber + 1
    }
    val audioQuality = arrayOf("320 kbps","256 kbps","192 kbps")
    println("Found $partnumber parts with duration of $durationOfPart seconds")
    println("Analyzing data with AudD, please wait ...")
    val myTrack = Track("Modeselektor", "The White Flash","", "Thom Yorke", false, "Bpitch Control")
    myTrack.play()
}

fun acceptableMixUrl(URL: String?) : String {
    var isValidChoice = false
    var userURL = ""
    //Loop until the user enters a valid url
    while (!isValidChoice) {
        //Ask the user to enter URL name
        // Read the user input
        if (URL != null) {
            isValidChoice = true
            userURL = URL
            println("Correct URL")
        }
        //if the choice is invalid, inform the user
        if (!isValidChoice) println("You must enter a correct url")
    }
    return userURL
}
