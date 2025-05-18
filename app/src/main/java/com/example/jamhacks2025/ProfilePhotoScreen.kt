package com.example.jamhacks2025
import java.io.File
import androidx.compose.ui.layout.ContentScale
import androidx.core.content.FileProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


@Composable
fun ProfilePhotoScreen(navController: NavController? = null) {
    AndroidCompact2(modifier = Modifier, navController)
}

@Composable
fun AndroidCompact2(modifier: Modifier = Modifier, navController: NavController? = null) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(UserManager.profilePhotoUri) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            UserManager.profilePhotoUri = uri
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            selectedImageUri = tempCameraUri
            UserManager.profilePhotoUri = tempCameraUri
        }
    }

    fun launchCamera() {
        val tempFile = File.createTempFile("profile_pic", ".jpg", context.cacheDir).apply { deleteOnExit() }
        tempCameraUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", tempFile)
        cameraLauncher.launch(tempCameraUri)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xffc16e70))
            .padding(24.dp)
    ) {
        Text(
            text = "Add a profile photo",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 50.sp,
            color = Color(0xfff2f3d9),
            modifier = Modifier.offset(y = 100.dp, x=35.dp)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 62.dp, y = 306.dp)
                .size(240.dp)
                .clip(CircleShape)
                .background(Color(0xfff2f3d9))
                .clickable {
                    // Show options (Gallery or Camera)
                    // For demo, we'll directly launch gallery. Replace with a dialog if needed.
                    galleryLauncher.launch("image/*")
                    // To directly open the camera instead: launchCamera()
                }
        ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Selected Profile Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text("+", color = Color(0xff151e3f), fontSize = 48.sp, fontWeight = FontWeight.Bold)
            }
        }

        Text(
            text = "This is how you’ll show up to others.",
            color = Color(0xfff2f3d9),
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.TopStart).offset(x = 56.dp, y = 599.dp)
        )

        Button(
            onClick = {
                navController?.navigate("skill_selection")
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff2f3d9)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .fillMaxWidth(0.8f)
                .height(60.dp)
        ) {
            Text("Next", style = MaterialTheme.typography.bodyLarge, color = Color(0xffdc9e82))
        }
    }
}

@Preview(widthDp = 412, heightDp = 917)
@Composable
private fun AndroidCompact2Preview() {
    AndroidCompact2()
}
