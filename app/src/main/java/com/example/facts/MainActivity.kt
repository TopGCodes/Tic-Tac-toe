package com.example.facts

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

        var gameState = intArrayOf(2,2,2,2,2,2,2,2,2)
        var ActivePlayer = 1
        var count = 0
        var GameActive = true
        var WinnnigPositions = arrayOf(
             intArrayOf(0, 1, 2),
             intArrayOf(3, 4, 5),
             intArrayOf(6, 7, 8),
             intArrayOf(0, 3, 6),
             intArrayOf(1, 4, 7),
             intArrayOf(2, 5, 8),
             intArrayOf(0, 4, 8),
             intArrayOf(2, 4, 6)
         )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
     fun dropIn(view : View)
     {
         val encounter = view as ImageView
         val TappedCell = encounter.tag.toString().toInt()
         val txt = findViewById<TextView>(R.id.winner1)
         val layout = findViewById<LinearLayout>(R.id.winner)

         if(gameState[TappedCell] ==  2 && GameActive)
         {
             if(ActivePlayer == 1)
             {
                 gameState[TappedCell] = 1
                 count++;
                 encounter.setImageResource(R.drawable.realx)
                 ActivePlayer = 0
             }
             else
             {
                 gameState[TappedCell] = 0
                 count++;
                 encounter.setImageResource(R.drawable.realo)
                 ActivePlayer = 1
             }

             encounter.translationY = -1000f
             encounter.animate().translationYBy(1000f).rotationY(1800f).duration = 100


             for(WinningPosition in WinnnigPositions)
             {
                 if(gameState[WinningPosition[0]] == gameState[WinningPosition[1]]    && gameState[WinningPosition[1]]  == gameState[WinningPosition[2]] && gameState[WinningPosition[0]] != 2 )
                 {
                     if(gameState[WinningPosition[0]]  == 0)
                     {
                         txt.text = "Red Player Wins"

                     }
                    else if(gameState[WinningPosition[0]]  == 1)
                     {
                         txt.text = "Green Player Wins"

                     }
                     layout.visibility = View.VISIBLE
                     GameActive = false
                 }
             }

             if(GameActive && count  == 9)
             {
                 txt.text = "DRAW"
                 layout.visibility = View.VISIBLE
                 GameActive = false
             }
         }
     }


    fun playAgain(view: View?) {
        ActivePlayer = 1
        GameActive = true
        count= 0
        val linearLayout = findViewById<LinearLayout>(R.id.winner)
        val gridLayout =
            findViewById<GridLayout>(R.id.gridLayout)
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        linearLayout.visibility = View.INVISIBLE
        for (i in 0 until gridLayout.childCount) {
            (gridLayout.getChildAt(i) as ImageView).setImageResource(0) //p t n
        }
    }





}