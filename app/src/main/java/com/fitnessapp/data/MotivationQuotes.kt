package com.fitnessapp.data

object MotivationQuotes {
    
    val quotes = listOf(
        "💪 Every workout counts! Your body is getting stronger with each rep.",
        "🔥 Don't wait for motivation. Create it through action!",
        "⚡ Your only limit is you. Push beyond your comfort zone today!",
        "🎯 Consistency is key. Show up even when you don't feel like it.",
        "🌟 The pain you feel today will be the strength you feel tomorrow.",
        "🏆 Success doesn't come from what you do occasionally, but what you do consistently.",
        "💯 Your health is an investment, not an expense. Exercise now!",
        "🚀 Strong body, strong mind. Let's get moving!",
        "⭐ You're one workout away from a good mood. Let's do this!",
        "💥 Transform your body, transform your life. Start now!",
        "🎪 Exercise is a celebration of what your body can do!",
        "🌈 Fitness is not about being better than others, it's about being better than you used to be.",
        "🔋 Energy flows where attention goes. Focus on your fitness!",
        "🎨 Sculpt your body like a work of art. You are the artist!",
        "⚡️ Movement is medicine. Take your daily dose!",
        "🌺 A healthy outside starts from the inside. Nourish and move!",
        "🎯 Set a goal so big that you can't achieve it until you grow into the person who can.",
        "💎 Your body can do it, it's your mind you need to convince.",
        "🌟 Train insane or remain the same. Choose progress!",
        "🏋️ Sweat is fat crying. Make it weep!",
        "⚡ You don't have to be great to start, but you have to start to be great.",
        "🔥 Make yourself proud. No one else can do it for you!",
        "💪 The best project you'll ever work on is YOU!",
        "🎯 Strive for progress, not perfection. Every step counts!",
        "🌟 Fall in love with taking care of your body!",
        "💯 Your future self will thank you for the workout you do today!",
        "🚀 Believe in yourself and you will be unstoppable!",
        "⭐ Champions train, losers complain. Which one are you?",
        "🔋 Exercise = instant happy pill. Take it now!",
        "💥 The only bad workout is the one that didn't happen. Let's go!"
    )
    
    fun getRandomQuote(): String {
        return quotes.random()
    }
    
    fun getQuoteAtIndex(index: Int): String {
        return quotes[index % quotes.size]
    }
}
