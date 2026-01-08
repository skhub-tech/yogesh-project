package com.fitnessapp.data

import com.fitnessapp.model.ExerciseVariation

object ExerciseVariationsData {
    
    fun getVariationsForExercise(exerciseName: String): List<ExerciseVariation> {
        return when (exerciseName.lowercase()) {
            "push-ups" -> pushUpVariations
            "bench press" -> benchPressVariations
            "incline dumbbell press" -> dumbbellPressVariations
            "pull-ups" -> pullUpVariations
            "dumbbell rows" -> rowVariations
            "squats" -> squatVariations
            "deadlift" -> deadliftVariations
            "lunges" -> lungeVariations
            "leg press" -> legPressVariations
            "overhead press" -> overheadPressVariations
            "lateral raises" -> lateralRaiseVariations
            "bicep curls" -> bicepCurlVariations
            "tricep dips" -> tricepDipVariations
            "plank" -> plankVariations
            "crunches" -> crunchVariations
            else -> emptyList()
        }
    }
    
    private val pushUpVariations = listOf(
        ExerciseVariation(
            id = "pushup_standard",
            name = "Standard Push-up",
            description = "Classic push-up with hands shoulder-width apart",
            difficulty = "Beginner",
            instructions = listOf(
                "Start in plank position with hands shoulder-width apart",
                "Lower body until chest nearly touches floor",
                "Push back up to starting position",
                "Keep core tight throughout"
            ),
            targetMuscles = "Chest, Triceps, Shoulders",
            equipment = "None",
            stepImages = listOf(
                com.fitnessapp.R.drawable.pushup_step1,
                com.fitnessapp.R.drawable.pushup_step2,
                com.fitnessapp.R.drawable.pushup_step3,
                com.fitnessapp.R.drawable.pushup_step4
            )
        ),
        ExerciseVariation(
            id = "pushup_wide",
            name = "Wide Push-up",
            description = "Hands placed wider than shoulders to target chest more",
            difficulty = "Beginner",
            instructions = listOf(
                "Place hands wider than shoulder-width",
                "Lower chest to ground while keeping elbows out",
                "Push back up explosively",
                "Focus on chest contraction"
            ),
            targetMuscles = "Chest (outer), Shoulders",
            equipment = "None",
            stepImages = listOf(
                com.fitnessapp.R.drawable.wide_pushup_step1,
                com.fitnessapp.R.drawable.wide_pushup_step2,
                com.fitnessapp.R.drawable.wide_pushup_step3,
                com.fitnessapp.R.drawable.wide_pushup_step4
            )
        ),
        ExerciseVariation(
            id = "pushup_diamond",
            name = "Diamond Push-up",
            description = "Hands together forming diamond shape for triceps emphasis",
            difficulty = "Intermediate",
            instructions = listOf(
                "Place hands together forming diamond with thumbs and fingers",
                "Keep elbows close to body",
                "Lower until chest touches hands",
                "Press back up focusing on triceps"
            ),
            targetMuscles = "Triceps, Inner Chest",
            equipment = "None",
            animationGif = com.fitnessapp.R.drawable.diamond_pushup_anim
        ),
        ExerciseVariation(
            id = "pushup_decline",
            name = "Decline Push-up",
            description = "Feet elevated to increase difficulty and target upper chest",
            difficulty = "Intermediate",
            instructions = listOf(
                "Place feet on elevated surface (bench/chair)",
                "Hands on ground shoulder-width apart",
                "Lower chest to ground",
                "Push back up with control"
            ),
            targetMuscles = "Upper Chest, Shoulders",
            equipment = "Bench or elevated surface",
            stepImages = listOf(
                com.fitnessapp.R.drawable.decline_pushup_step1,
                com.fitnessapp.R.drawable.decline_pushup_step2,
                com.fitnessapp.R.drawable.decline_pushup_step3,
                com.fitnessapp.R.drawable.decline_pushup_step4
            )
        ),
        ExerciseVariation(
            id = "pushup_incline",
            name = "Incline Push-up",
            description = "Hands elevated for easier variation",
            difficulty = "Beginner",
            instructions = listOf(
                "Place hands on elevated surface",
                "Feet on ground",
                "Lower chest to surface",
                "Push back to start"
            ),
            targetMuscles = "Lower Chest, Triceps",
            equipment = "Bench or elevated surface",
            stepImages = listOf(
                com.fitnessapp.R.drawable.incline_pushup_step1,
                com.fitnessapp.R.drawable.incline_pushup_step2,
                com.fitnessapp.R.drawable.incline_pushup_step3,
                com.fitnessapp.R.drawable.incline_pushup_step4
            )
        ),
        ExerciseVariation(
            id = "pushup_pike",
            name = "Pike Push-up",
            description = "Hips raised for shoulder emphasis",
            difficulty = "Intermediate",
            instructions = listOf(
                "Start in downward dog position",
                "Lower head toward ground",
                "Push back up using shoulders",
                "Keep hips high throughout"
            ),
            targetMuscles = "Shoulders, Upper Chest",
            equipment = "None",
            stepImages = listOf(
                com.fitnessapp.R.drawable.pike_pushup_step1,
                com.fitnessapp.R.drawable.pike_pushup_step2,
                com.fitnessapp.R.drawable.pike_pushup_step3,
                com.fitnessapp.R.drawable.pike_pushup_step4
            )
        ),
        ExerciseVariation(
            id = "pushup_archer",
            name = "Archer Push-up",
            description = "One-arm emphasis alternating sides",
            difficulty = "Advanced",
            instructions = listOf(
                "Start wide stance",
                "Shift weight to one side while lowering",
                "Other arm stays extended",
                "Alternate sides each rep"
            ),
            targetMuscles = "Chest, Triceps (unilateral)",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "pushup_onearm",
            name = "One-Arm Push-up",
            description = "Ultimate push-up challenge",
            difficulty = "Advanced",
            instructions = listOf(
                "One hand on ground, other behind back",
                "Wide stance for stability",
                "Lower with control",
                "Push back up using chest and arm"
            ),
            targetMuscles = "Chest, Triceps, Core",
            equipment = "None"
        )
    )
    
    private val benchPressVariations = listOf(
        ExerciseVariation(
            id = "bench_flat",
            name = "Flat Bench Press",
            description = "Classic barbell bench press",
            difficulty = "Intermediate",
            instructions = listOf(
                "Lie on flat bench, feet on floor",
                "Grip barbell slightly wider than shoulders",
                "Lower bar to chest",
                "Press up until arms extended"
            ),
            targetMuscles = "Chest, Triceps, Shoulders",
            equipment = "Barbell, Bench",
            stepImages = listOf(
                com.fitnessapp.R.drawable.bench_flat_step1,
                com.fitnessapp.R.drawable.bench_flat_step2
            )
        ),
        ExerciseVariation(
            id = "bench_incline",
            name = "Incline Bench Press",
            description = "Upper chest focus with inclined bench",
            difficulty = "Intermediate",
            instructions = listOf(
                "Set bench to 30-45 degree incline",
                "Grip barbell shoulder-width",
                "Lower to upper chest",
                "Press up explosively"
            ),
            targetMuscles = "Upper Chest, Shoulders",
            equipment = "Barbell, Incline Bench",
            stepImages = listOf(
                com.fitnessapp.R.drawable.bench_incline_step1
            )
        ),
        ExerciseVariation(
            id = "bench_decline",
            name = "Decline Bench Press",
            description = "Lower chest emphasis",
            difficulty = "Intermediate",
            instructions = listOf(
                "Lie on decline bench, secure feet",
                "Lower bar to lower chest",
                "Press up with control",
                "Keep shoulders back"
            ),
            targetMuscles = "Lower Chest, Triceps",
            equipment = "Barbell, Decline Bench"
        ),
        ExerciseVariation(
            id = "bench_closegrip",
            name = "Close-Grip Bench Press",
            description = "Triceps-focused variation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Grip barbell hands 6-8 inches apart",
                "Keep elbows tucked",
                "Lower to chest",
                "Press focusing on triceps"
            ),
            targetMuscles = "Triceps, Inner Chest",
            equipment = "Barbell, Bench"
        ),
        ExerciseVariation(
            id = "bench_dumbbell",
            name = "Dumbbell Bench Press",
            description = "Greater range of motion with dumbbells",
            difficulty = "Intermediate",
            instructions = listOf(
                "Hold dumbbells above chest",
                "Lower until stretch in chest",
                "Press up and together",
                "Keep core tight"
            ),
            targetMuscles = "Chest, Stabilizers",
            equipment = "Dumbbells, Bench"
        ),
        ExerciseVariation(
            id = "bench_widegrip",
            name = "Wide-Grip Bench Press",
            description = "Outer chest emphasis",
            difficulty = "Intermediate",
            instructions = listOf(
                "Grip wider than standard",
                "Lower with elbows out",
                "Press up feeling chest stretch",
                "Control the weight"
            ),
            targetMuscles = "Outer Chest, Shoulders",
            equipment = "Barbell, Bench"
        )
    )
    
    private val dumbbellPressVariations = listOf(
        ExerciseVariation(
            id = "db_incline",
            name = "Incline Dumbbell Press",
            description = "Upper chest development",
            difficulty = "Intermediate",
            instructions = listOf(
                "Set bench to 30-45 degrees",
                "Hold dumbbells at chest level",
                "Press up and slightly together",
                "Lower with control"
            ),
            targetMuscles = "Upper Chest, Shoulders",
            equipment = "Dumbbells, Incline Bench"
        ),
        ExerciseVariation(
            id = "db_fly",
            name = "Dumbbell Fly",
            description = "Chest isolation exercise",
            difficulty = "Beginner",
            instructions = listOf(
                "Lie on bench with dumbbells above chest",
                "Lower in arc motion, slight bend in elbows",
                "Feel stretch in chest",
                "Bring dumbbells back together"
            ),
            targetMuscles = "Chest (isolation)",
            equipment = "Dumbbells, Bench"
        ),
        ExerciseVariation(
            id = "db_squeeze",
            name = "Dumbbell Squeeze Press",
            description = "Constant tension on chest",
            difficulty = "Intermediate",
            instructions = listOf(
                "Hold dumbbells together above chest",
                "Squeeze dumbbells together throughout",
                "Lower while maintaining squeeze",
                "Press back up"
            ),
            targetMuscles = "Inner Chest",
            equipment = "Dumbbells, Bench"
        )
    )
    
    private val pullUpVariations = listOf(
        ExerciseVariation(
            id = "pullup_standard",
            name = "Standard Pull-up",
            description = "Classic overhand pull-up",
            difficulty = "Intermediate",
            instructions = listOf(
                "Grip bar with palms facing away",
                "Hands shoulder-width apart",
                "Pull until chin over bar",
                "Lower with control"
            ),
            targetMuscles = "Lats, Biceps, Back",
            equipment = "Pull-up bar"
        ),
        ExerciseVariation(
            id = "pullup_wide",
            name = "Wide-Grip Pull-up",
            description = "Lat width emphasis",
            difficulty = "Advanced",
            instructions = listOf(
                "Grip wider than shoulders",
                "Pull elbows down and back",
                "Focus on lat contraction",
                "Full range of motion"
            ),
            targetMuscles = "Lats (width), Upper Back",
            equipment = "Pull-up bar"
        ),
        ExerciseVariation(
            id = "pullup_close",
            name = "Close-Grip Pull-up",
            description = "Biceps and lower lat focus",
            difficulty = "Intermediate",
            instructions = listOf(
                "Hands close together",
                "Pull straight up",
                "Squeeze lats at top",
                "Control descent"
            ),
            targetMuscles = "Lower Lats, Biceps",
            equipment = "Pull-up bar"
        ),
        ExerciseVariation(
            id = "chinup",
            name = "Chin-up",
            description = "Underhand grip for biceps",
            difficulty = "Intermediate",
            instructions = listOf(
                "Grip bar with palms facing you",
                "Pull up using biceps and back",
                "Chin over bar",
                "Lower slowly"
            ),
            targetMuscles = "Biceps, Lats",
            equipment = "Pull-up bar"
        ),
        ExerciseVariation(
            id = "pullup_neutral",
            name = "Neutral-Grip Pull-up",
            description = "Easier on shoulders and wrists",
            difficulty = "Intermediate",
            instructions = listOf(
                "Use parallel handles, palms facing each other",
                "Pull up between handles",
                "Engage lats and arms",
                "Full range of motion"
            ),
            targetMuscles = "Lats, Biceps, Forearms",
            equipment = "Neutral grip bar attachment"
        )
    )
    
    private val rowVariations = listOf(
        ExerciseVariation(
            id = "row_dumbbell",
            name = "Single-Arm Dumbbell Row",
            description = "Unilateral back development",
            difficulty = "Beginner",
            instructions = listOf(
                "Place knee and hand on bench",
                "Hold dumbbell in other hand",
                "Pull elbow back to hip",
                "Squeeze back at top"
            ),
            targetMuscles = "Lats, Mid-back, Biceps",
            equipment = "Dumbbell, Bench"
        ),
        ExerciseVariation(
            id = "row_bent",
            name = "Bent-Over Barbell Row",
            description = "Mass builder for back",
            difficulty = "Intermediate",
            instructions = listOf(
                "Bend at hips, back straight",
                "Pull barbell to lower chest",
                "Squeeze shoulder blades",
                "Lower with control"
            ),
            targetMuscles = "Lats, Traps, Rhomboids",
            equipment = "Barbell"
        ),
        ExerciseVariation(
            id = "row_tbar",
            name = "T-Bar Row",
            description = "Thick back developer",
            difficulty = "Intermediate",
            instructions = listOf(
                "Straddle T-bar, bend at hips",
                "Pull handle to chest",
                "Keep back straight",
                "Control the movement"
            ),
            targetMuscles = "Mid-back, Lats",
            equipment = "T-bar machine"
        )
    )
    
    private val squatVariations = listOf(
        ExerciseVariation(
            id = "squat_bodyweight",
            name = "Bodyweight Squat",
            description = "Foundation squat pattern",
            difficulty = "Beginner",
            instructions = listOf(
                "Feet shoulder-width apart",
                "Lower hips back and down",
                "Keep chest up, knees out",
                "Stand back up"
            ),
            targetMuscles = "Quads, Glutes, Hamstrings",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "squat_goblet",
            name = "Goblet Squat",
            description = "Great for learning squat form",
            difficulty = "Beginner",
            instructions = listOf(
                "Hold dumbbell at chest",
                "Squat down between legs",
                "Elbows inside knees",
                "Drive through heels"
            ),
            targetMuscles = "Quads, Glutes, Core",
            equipment = "Dumbbell"
        ),
        ExerciseVariation(
            id = "squat_front",
            name = "Front Squat",
            description = "Quad-focused with upright torso",
            difficulty = "Advanced",
            instructions = listOf(
                "Barbell on front shoulders",
                "Elbows high",
                "Squat keeping torso upright",
                "Drive up through heels"
            ),
            targetMuscles = "Quads, Core",
            equipment = "Barbell, Rack"
        ),
        ExerciseVariation(
            id = "squat_back",
            name = "Back Squat",
            description = "King of leg exercises",
            difficulty = "Intermediate",
            instructions = listOf(
                "Barbell across upper back",
                "Feet shoulder-width",
                "Squat to parallel or below",
                "Keep core braced"
            ),
            targetMuscles = "Quads, Glutes, Hamstrings, Core",
            equipment = "Barbell, Rack"
        ),
        ExerciseVariation(
            id = "squat_bulgarian",
            name = "Bulgarian Split Squat",
            description = "Unilateral leg strength",
            difficulty = "Intermediate",
            instructions = listOf(
                "Rear foot elevated on bench",
                "Lower front knee",
                "Keep torso upright",
                "Drive through front heel"
            ),
            targetMuscles = "Quads, Glutes (unilateral)",
            equipment = "Bench, optional dumbbells"
        ),
        ExerciseVariation(
            id = "squat_sumo",
            name = "Sumo Squat",
            description = "Wide stance for inner thighs",
            difficulty = "Beginner",
            instructions = listOf(
                "Wide stance, toes out",
                "Lower straight down",
                "Knees track over toes",
                "Squeeze glutes at top"
            ),
            targetMuscles = "Inner Thighs, Glutes, Quads",
            equipment = "None or dumbbell"
        )
    )
    
    private val deadliftVariations = listOf(
        ExerciseVariation(
            id = "deadlift_conventional",
            name = "Conventional Deadlift",
            description = "Classic deadlift form",
            difficulty = "Advanced",
            instructions = listOf(
                "Feet hip-width, bar over midfoot",
                "Grip just outside legs",
                "Hinge at hips, back straight",
                "Drive through heels, extend hips"
            ),
            targetMuscles = "Hamstrings, Glutes, Back, Core",
            equipment = "Barbell"
        ),
        ExerciseVariation(
            id = "deadlift_sumo",
            name = "Sumo Deadlift",
            description = "Wide stance variation",
            difficulty = "Advanced",
            instructions = listOf(
                "Wide stance, toes out",
                "Grip inside legs",
                "Upright torso",
                "Drive knees out, extend hips"
            ),
            targetMuscles = "Glutes, Inner Thighs, Back",
            equipment = "Barbell"
        ),
        ExerciseVariation(
            id = "deadlift_romanian",
            name = "Romanian Deadlift",
            description = "Hamstring and glute emphasis",
            difficulty = "Intermediate",
            instructions = listOf(
                "Start standing with barbell",
                "Slight knee bend",
                "Hinge at hips, lower bar",
                "Feel hamstring stretch, return"
            ),
            targetMuscles = "Hamstrings, Glutes, Lower Back",
            equipment = "Barbell"
        ),
        ExerciseVariation(
            id = "deadlift_singleleg",
            name = "Single-Leg Deadlift",
            description = "Balance and unilateral strength",
            difficulty = "Intermediate",
            instructions = listOf(
                "Stand on one leg",
                "Hinge forward extending other leg back",
                "Lower weight toward ground",
                "Return to standing"
            ),
            targetMuscles = "Hamstrings, Glutes, Balance",
            equipment = "Dumbbell or kettlebell"
        ),
        ExerciseVariation(
            id = "deadlift_trap",
            name = "Trap Bar Deadlift",
            description = "Easier on lower back",
            difficulty = "Intermediate",
            instructions = listOf(
                "Stand inside trap bar",
                "Grip handles at sides",
                "Squat down, back straight",
                "Drive up through heels"
            ),
            targetMuscles = "Quads, Glutes, Traps",
            equipment = "Trap bar"
        )
    )
    

    
    private val lungeVariations = listOf(
        ExerciseVariation(
            id = "lunge_forward",
            name = "Forward Lunge",
            description = "Classic forward stepping lunge",
            difficulty = "Beginner",
            instructions = listOf(
                "Stand with feet hip-width apart",
                "Step forward with one leg",
                "Lower hips until both knees at 90 degrees",
                "Push back to starting position"
            ),
            targetMuscles = "Quads, Glutes, Hamstrings",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "lunge_reverse",
            name = "Reverse Lunge",
            description = "Step backward instead of forward",
            difficulty = "Beginner",
            instructions = listOf(
                "Stand with feet together",
                "Step backward with one leg",
                "Lower back knee toward ground",
                "Return to starting position"
            ),
            targetMuscles = "Glutes, Quads, Hamstrings",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "lunge_walking",
            name = "Walking Lunge",
            description = "Continuous forward lunges",
            difficulty = "Intermediate",
            instructions = listOf(
                "Perform forward lunge",
                "Instead of returning, step forward with back leg",
                "Continue alternating legs while moving forward",
                "Maintain upright torso"
            ),
            targetMuscles = "Quads, Glutes, Balance",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "lunge_lateral",
            name = "Lateral Lunge",
            description = "Side-to-side lunge variation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Stand with feet wide",
                "Shift weight to one side, bending that knee",
                "Keep other leg straight",
                "Push back to center"
            ),
            targetMuscles = "Inner Thighs, Glutes, Quads",
            equipment = "None"
        )
    )
    
    private val legPressVariations = listOf(
        ExerciseVariation(
            id = "legpress_standard",
            name = "Standard Leg Press",
            description = "Classic leg press machine exercise",
            difficulty = "Beginner",
            instructions = listOf(
                "Sit in leg press machine",
                "Place feet shoulder-width on platform",
                "Lower weight by bending knees",
                "Press back to start without locking knees"
            ),
            targetMuscles = "Quads, Glutes, Hamstrings",
            equipment = "Leg press machine"
        ),
        ExerciseVariation(
            id = "legpress_wide",
            name = "Wide Stance Leg Press",
            description = "Targets inner thighs more",
            difficulty = "Beginner",
            instructions = listOf(
                "Place feet wider than shoulders on platform",
                "Toes pointed slightly outward",
                "Lower weight with control",
                "Press through heels"
            ),
            targetMuscles = "Quads, Inner Thighs, Glutes",
            equipment = "Leg press machine"
        ),
        ExerciseVariation(
            id = "legpress_high",
            name = "High Foot Placement",
            description = "Emphasizes glutes and hamstrings",
            difficulty = "Intermediate",
            instructions = listOf(
                "Place feet high on platform",
                "Heels near top edge",
                "Push through heels",
                "Feel glutes and hamstrings engage"
            ),
            targetMuscles = "Glutes, Hamstrings",
            equipment = "Leg press machine"
        )
    )
    
    private val overheadPressVariations = listOf(
        ExerciseVariation(
            id = "ohp_barbell",
            name = "Barbell Overhead Press",
            description = "Standing overhead press with barbell",
            difficulty = "Intermediate",
            instructions = listOf(
                "Stand with barbell at shoulder height",
                "Grip slightly wider than shoulders",
                "Press bar overhead until arms extended",
                "Lower with control"
            ),
            targetMuscles = "Shoulders, Triceps, Core",
            equipment = "Barbell"
        ),
        ExerciseVariation(
            id = "ohp_dumbbell",
            name = "Dumbbell Shoulder Press",
            description = "Seated or standing dumbbell press",
            difficulty = "Beginner",
            instructions = listOf(
                "Hold dumbbells at shoulder height",
                "Palms facing forward",
                "Press dumbbells overhead",
                "Lower to shoulders"
            ),
            targetMuscles = "Shoulders, Triceps",
            equipment = "Dumbbells"
        ),
        ExerciseVariation(
            id = "ohp_arnold",
            name = "Arnold Press",
            description = "Rotating dumbbell press variation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Start with dumbbells at shoulders, palms facing you",
                "Rotate palms forward while pressing up",
                "Finish with palms forward overhead",
                "Reverse motion on way down"
            ),
            targetMuscles = "All three deltoid heads",
            equipment = "Dumbbells"
        )
    )
    
    private val lateralRaiseVariations = listOf(
        ExerciseVariation(
            id = "lateral_standing",
            name = "Standing Lateral Raise",
            description = "Classic side delt isolation",
            difficulty = "Beginner",
            instructions = listOf(
                "Stand with dumbbells at sides",
                "Raise arms out to sides",
                "Lift to shoulder height",
                "Lower with control"
            ),
            targetMuscles = "Side Deltoids",
            equipment = "Dumbbells"
        ),
        ExerciseVariation(
            id = "lateral_cable",
            name = "Cable Lateral Raise",
            description = "Constant tension variation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Stand sideways to cable machine",
                "Grab handle with far hand",
                "Raise arm out to side",
                "Control the descent"
            ),
            targetMuscles = "Side Deltoids",
            equipment = "Cable machine"
        ),
        ExerciseVariation(
            id = "lateral_bent",
            name = "Bent-Over Lateral Raise",
            description = "Targets rear deltoids",
            difficulty = "Intermediate",
            instructions = listOf(
                "Bend forward at hips",
                "Hold dumbbells beneath shoulders",
                "Raise arms out to sides",
                "Focus on rear delts"
            ),
            targetMuscles = "Rear Deltoids",
            equipment = "Dumbbells"
        )
    )
    
    private val bicepCurlVariations = listOf(
        ExerciseVariation(
            id = "curl_barbell",
            name = "Barbell Curl",
            description = "Classic bicep builder",
            difficulty = "Beginner",
            instructions = listOf(
                "Stand with barbell, palms up",
                "Curl bar toward shoulders",
                "Keep elbows stationary",
                "Lower with control"
            ),
            targetMuscles = "Biceps",
            equipment = "Barbell"
        ),
        ExerciseVariation(
            id = "curl_dumbbell",
            name = "Alternating Dumbbell Curl",
            description = "Unilateral bicep work",
            difficulty = "Beginner",
            instructions = listOf(
                "Hold dumbbells at sides",
                "Curl one dumbbell up",
                "Rotate palm to face shoulder",
                "Alternate arms"
            ),
            targetMuscles = "Biceps",
            equipment = "Dumbbells"
        ),
        ExerciseVariation(
            id = "curl_hammer",
            name = "Hammer Curl",
            description = "Targets brachialis and forearms",
            difficulty = "Beginner",
            instructions = listOf(
                "Hold dumbbells with neutral grip (palms facing each other)",
                "Curl up keeping palms facing inward",
                "Lower with control",
                "Keep elbows tight to body"
            ),
            targetMuscles = "Biceps, Brachialis, Forearms",
            equipment = "Dumbbells"
        ),
        ExerciseVariation(
            id = "curl_preacher",
            name = "Preacher Curl",
            description = "Isolated bicep curl variation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Sit at preacher bench",
                "Arms resting on pad",
                "Curl weight toward shoulders",
                "Full range of motion"
            ),
            targetMuscles = "Biceps (peak)",
            equipment = "Preacher bench, barbell or dumbbells"
        )
    )
    
    private val tricepDipVariations = listOf(
        ExerciseVariation(
            id = "dip_parallel",
            name = "Parallel Bar Dips",
            description = "Bodyweight tricep and chest builder",
            difficulty = "Intermediate",
            instructions = listOf(
                "Support yourself on parallel bars",
                "Lower body by bending elbows",
                "Lean forward for chest, upright for triceps",
                "Press back up to start"
            ),
            targetMuscles = "Triceps, Chest, Shoulders",
            equipment = "Parallel bars"
        ),
        ExerciseVariation(
            id = "dip_bench",
            name = "Bench Dips",
            description = "Easier variation using bench",
            difficulty = "Beginner",
            instructions = listOf(
                "Sit on bench edge, hands beside hips",
                "Slide hips off bench",
                "Lower body by bending elbows",
                "Push back up"
            ),
            targetMuscles = "Triceps",
            equipment = "Bench"
        ),
        ExerciseVariation(
            id = "dip_weighted",
            name = "Weighted Dips",
            description = "Advanced variation with added weight",
            difficulty = "Advanced",
            instructions = listOf(
                "Attach weight to dip belt",
                "Perform parallel bar dips",
                "Maintain control throughout",
                "Progressive overload"
            ),
            targetMuscles = "Triceps, Chest",
            equipment = "Parallel bars, weight belt"
        )
    )
    
    private val plankVariations = listOf(
        ExerciseVariation(
            id = "plank_standard",
            name = "Standard Plank",
            description = "Basic forearm plank hold",
            difficulty = "Beginner",
            instructions = listOf(
                "Rest on forearms and toes",
                "Body straight from head to heels",
                "Engage core, don't let hips sag",
                "Hold position"
            ),
            targetMuscles = "Core, Abs, Lower Back",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "plank_side",
            name = "Side Plank",
            description = "Lateral core stability",
            difficulty = "Intermediate",
            instructions = listOf(
                "Lie on side, prop up on one forearm",
                "Stack feet or stagger them",
                "Raise hips off ground",
                "Hold straight line from head to feet"
            ),
            targetMuscles = "Obliques, Core",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "plank_renegade",
            name = "Plank with Shoulder Tap",
            description = "Dynamic plank variation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Start in high plank position",
                "Tap left hand to right shoulder",
                "Return to plank",
                "Alternate sides, minimize rotation"
            ),
            targetMuscles = "Core, Shoulders, Stability",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "plank_up_down",
            name = "Up-Down Plank",
            description = "Moving between forearm and high plank",
            difficulty = "Advanced",
            instructions = listOf(
                "Start in forearm plank",
                "Push up to high plank one arm at a time",
                "Lower back to forearms",
                "Alternate leading arm"
            ),
            targetMuscles = "Core, Triceps, Shoulders",
            equipment = "None"
        )
    )
    
    private val crunchVariations = listOf(
        ExerciseVariation(
            id = "crunch_basic",
            name = "Basic Crunch",
            description = "Classic abdominal exercise",
            difficulty = "Beginner",
            instructions = listOf(
                "Lie on back, knees bent",
                "Hands behind head or crossed on chest",
                "Lift shoulders off ground",
                "Focus on contracting abs"
            ),
            targetMuscles = "Upper Abs",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "crunch_bicycle",
            name = "Bicycle Crunches",
            description = "Dynamic ab exercise with rotation",
            difficulty = "Intermediate",
            instructions = listOf(
                "Lie on back, hands behind head",
                "Bring opposite elbow to opposite knee",
                "Extend other leg",
                "Alternate sides in pedaling motion"
            ),
            targetMuscles = "Abs, Obliques",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "crunch_reverse",
            name = "Reverse Crunch",
            description = "Targets lower abs",
            difficulty = "Intermediate",
            instructions = listOf(
                "Lie on back, legs raised",
                "Curl knees toward chest",
                "Lift hips slightly off ground",
                "Lower with control"
            ),
            targetMuscles = "Lower Abs",
            equipment = "None"
        ),
        ExerciseVariation(
            id = "crunch_cable",
            name = "Cable Crunch",
            description = "Weighted ab exercise",
            difficulty = "Intermediate",
            instructions = listOf(
                "Kneel in front of cable machine",
                "Hold rope attachment at head level",
                "Crunch down bringing elbows to knees",
                "Focus on abs, not pulling with arms"
            ),
            targetMuscles = "Abs",
            equipment = "Cable machine"
        )
    )
}
