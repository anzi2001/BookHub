package com.bookhub.bookhub.ui.screens.userprofile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bookhub.bookhub.R
import com.bookhub.bookhub.ui.common.HeightSpacer
import com.bookhub.bookhub.ui.common.WidthSpacer
import com.bookhub.bookhub.ui.screens.userprofile.components.RoundImage
import com.bookhub.bookhub.ui.theme.*

@Composable
fun UserProfileScreen() {
    Column {
        ProfileSection()
        HeightSpacer(height = 10.dp)
        RecentActivity()
    }

}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Brown)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "edit", tint = Color.White)
        }
        RoundImage(
            image = painterResource(id = R.drawable.profile_image),
            modifier = Modifier.size(100.dp)
        )
        Text("Anže Kocjančič", style = UserNameStyle, textAlign = TextAlign.Center)
        HeightSpacer(height = 10.dp)
        Row{
            Text("50 \nFollowers", style = FollowingStyle, textAlign = TextAlign.Center)
            WidthSpacer(16.dp)
            Text("12 \nFollowing", style = FollowingStyle, textAlign = TextAlign.Center)
        }
        HeightSpacer(height = 20.dp)
    }

}

@Composable
fun RecentActivity(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Recent Activity",
            style = AuthorStyle,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
        )
        SocialPost()
        SocialPost2()
    }
}

@Composable
fun SocialPost() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 5.dp)
            .background(LightGray)
            .clip(RoundedCornerShape(50.dp))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            RoundImage(
                image = painterResource(id = R.drawable.profile_image),
                modifier = Modifier.size(40.dp)
            )
            Text(
                "Anže K. read",
                style = FollowingStyle,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 0.dp)) {
            RoundImage(
                image = painterResource(id = R.drawable.profile_image),
                modifier = Modifier
                    .size(100.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Sarah J. Maas",
                    style = AuthorStyle,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(text = "A Court Of Thorns And Roses")
            }
        }
    }
}

@Composable
fun SocialPost2(){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(LightGray)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            RoundImage(
                image = painterResource(id = R.drawable.profile_image),
                modifier = Modifier
                    .size(40.dp)
            )
            Text(
                "Anže K. followed",
                style = FollowingStyle,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.profile_image),
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                "Nikita Galuh Kapušin",
                style = UserNameStyle,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            HeightSpacer(height = 16.dp)
        }
    }
}

@Preview
@Composable
fun UserProfileScreenPreview() {
    BookHubTheme {
        UserProfileScreen()
    }
}