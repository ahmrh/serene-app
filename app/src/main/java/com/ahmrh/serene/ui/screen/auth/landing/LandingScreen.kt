package com.ahmrh.serene.ui.screen.auth.landing

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.serene.R
import com.ahmrh.serene.ui.theme.SereneTheme

// TODO 1: create anonymous user login
@Composable
fun LandingScreen(
    navController: NavHostController = rememberNavController(),
){

    var index by remember{ mutableIntStateOf(1) }

    BackHandler {
        if(index > 0) index --
    }


    Scaffold {
        Surface(modifier = Modifier.padding(it)){

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when(index){
                    0 -> {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {


                            Spacer(
                                modifier = Modifier.height(80.dp)
                            )
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(painter = painterResource(id = R.drawable.serene_icon_lotus), contentDescription = null)
                                Text(
                                    "Serene",
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Bold)
                            }
                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.serene_landing),
                                contentDescription = null,
                                modifier = Modifier
                                    .wrapContentSize(
                                        unbounded = true,
                                        align = Alignment.Center
                                    )
                                    .size(width = 450.dp, height = 300.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            Text("Self-care companion\n for better lifestyle",
                                style = MaterialTheme.typography.bodyLarge)

                            Spacer(
                                modifier = Modifier.height(80.dp)
                            )

                            Button(
                                onClick = { index += 1 },
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                Text("Get Started")
                            }

                        }
                    }
                    1 -> {


                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Spacer(
                                modifier = Modifier.height(80.dp)
                            )


                            Image(
                                painter = painterResource(id = R.drawable.serene_setup_profile),
                                contentDescription = null,
                                modifier = Modifier
                                    .wrapContentSize(
                                        unbounded = true,
                                        align = Alignment.Center
                                    )
                                    .size(width = 450.dp, height = 300.dp),
                                contentScale = ContentScale.Crop
                            )

                            Divider(
                                modifier = Modifier.padding(vertical = 24.dp)
                            )
                            Text("Set up your profile", style = MaterialTheme.typography.titleLarge)

                            Spacer(modifier = Modifier.height(24.dp))

                            Column (
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){

                                Button(
                                    onClick = {},
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        contentColor = MaterialTheme.colorScheme.onSecondary
                                    )
                                ) {
                                    Text("Continue Anonymously")
                                }

                                Button(
                                    onClick = {},
                                    modifier = Modifier.fillMaxWidth(),

                                    ) {
                                    Text("Continue with Email")
                                }

//                                Text("OR")
//
//
//                                Button(
//                                    onClick = { index += 1},
//                                    modifier = Modifier.fillMaxWidth(),
//
//
//                                    ) {
//
//                                    Text("Continue with Google")
//                                }
                            }



                        }
                    }
                }




            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview(){
    SereneTheme{
        LandingScreen()
    }
}