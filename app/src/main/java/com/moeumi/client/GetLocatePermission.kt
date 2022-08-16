package com.moeumi.client

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class, ExperimentalAnimationApi::class)
@Composable
fun GetLocationPermission(
    content: @Composable () -> Unit, requestCompose: @Composable () -> Unit = {
        Text(
            text = "위치권한이 필요해요.",
        )
    }
) {
    val fineLocationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val isGranted = rememberSaveable { mutableStateOf(false) }

    when (fineLocationPermissionState.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {
            isGranted.value = true
        }
        is PermissionStatus.Denied -> {
            isGranted.value = false
        }
    }

    AnimatedVisibility(
        visible = isGranted.value,
        enter = fadeIn(animationSpec = tween(250)) +
                slideInVertically(animationSpec = tween(250), initialOffsetY = { 64 }),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                content()
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }

    AnimatedVisibility(
        !isGranted.value,
        exit = scaleOut(animationSpec = tween(250)) + fadeOut(animationSpec = tween(250))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            requestCompose()
            Button(
                onClick = { fineLocationPermissionState.launchPermissionRequest() },
                modifier = Modifier.height(64.dp),
            ) {
                Text(
                    "권한 요청하기", fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Preview
@Composable
fun GetLocationPermissionPreview() {
    GetLocationPermission(content = { Text("Hello World") })
}
